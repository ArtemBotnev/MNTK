package ru.artembotnev.mntk.model;

import java.io.IOException;
import java.util.List;

import ru.artembotnev.mntk.model.net.ApiProvider;
import ru.artembotnev.mntk.model.net.SectionApi;
import ru.artembotnev.mntk.model.net.pojo.ConferenceRoot;
import ru.artembotnev.mntk.model.net.pojo.Participant;

/**
 * Singleton for providing data to UI
 * <p>
 * Created by Artem Botnev on 20.05.2018
 */
public class DataManager {
    private static DataManager instance;
    private ConferenceRoot conferenceRoot;
    private List<Participant> participants;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public ConferenceRoot getConferenceRoot() throws IOException {
        if (conferenceRoot == null) {
            SectionApi sectionApi = ApiProvider.getSectionApiInstance();
            // getting ConferenceRoot by apiConferenceSection
            conferenceRoot = sectionApi.getConferenceRoot().execute().body();
        }

        return conferenceRoot;
    }

    public List<Participant> getParticipants(String id) throws IOException {
        if (participants == null) {
            SectionApi sectionApi = ApiProvider.getSectionApiInstance();
            // getting ConferenceRoot by apiConferenceSection
            participants = sectionApi.getParticipants(id).execute().body();
        }

        return participants;
    }
}
