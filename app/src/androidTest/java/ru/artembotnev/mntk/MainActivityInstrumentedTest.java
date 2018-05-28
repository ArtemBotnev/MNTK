package ru.artembotnev.mntk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.artembotnev.mntk.model.net.ApiProvider;
import ru.artembotnev.mntk.model.net.pojo.Participant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    private static Context appContext;

    @BeforeClass
    public static void initRealm() {
        appContext = InstrumentationRegistry.getTargetContext();
        final RealmConfiguration config = new RealmConfiguration.Builder()
                .name("participants.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @AfterClass
    public static void dropRealm() {
        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }

    @Test
    public void getParticipantsAndSafeToRealm() {
        final String conferenceIdDefault = "5adce40ae98b0504c10dded7";

        try {
            List<Participant> participants = ApiProvider.getSectionApiInstance()
                    .getParticipants(conferenceIdDefault)
                    .execute()
                    .body();

            assertNotNull(participants);
            assertFalse(participants.isEmpty());
            Participant participant = participants.get(0);
            assertEquals("Abdallah Khaled Atya Ahmed", participant.getLastName());

            Realm realm = Realm.getDefaultInstance();

            // add data to realm database
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(participants);
            realm.commitTransaction();

            Participant firstParticipant = realm.where(Participant.class)
                    .findFirst();
            assertNotNull(firstParticipant);

            Participant secondParticipant = realm.where(Participant.class)
                    .equalTo("_id", "5aedcbe1e126e0aafff56755")
                    .findFirst();
            assertNotNull(secondParticipant);

            assertEquals("Abdallah Khaled Atya Ahmed", firstParticipant.getLastName());
            assertEquals("Abdelgany Mohamed Adel Ahmed Mohamed", secondParticipant.getLastName());

            realm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
