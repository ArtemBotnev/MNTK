package ru.artembotnev.mntk;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import ru.artembotnev.mntk.model.DataManager;
import ru.artembotnev.mntk.model.net.pojo.ConferenceRoot;
import ru.artembotnev.mntk.model.net.pojo.Section;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Checks working of DataManager class
 * <p>
 * Created by Artem Botnev on 20.05.18
 */
public class DataManagerTest {
    private static DataManager instance;

    @BeforeClass
    public static void createInstance() {
        instance = DataManager.getInstance();
    }

    @AfterClass
    public static void destroyInstance() {
        instance = null;
    }

    @Test
    public void getConferenceRootTest() {
        try {
            ConferenceRoot root = instance.getConferenceRoot();
            rootCheck(root);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Data haven't been obtained");
        }
    }

    private void rootCheck(ConferenceRoot root) {
        // make sure if data haven't changed
        assertNotNull(root);
        assertFalse(root.majorSections.isEmpty());
        assertEquals(1527055200000L, root.startDateTimeMs);
        assertEquals(
                "Безопасность, эффективность и экономика атомной энергетики МНТК-2018",
                root.name);

        // check date
        Date date = root.startDateTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(2018, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.MAY, calendar.get(Calendar.MONTH));

        // check second major section
        majorSectionCheck(root.majorSections.get(1));
    }

    private void majorSectionCheck(Section majorSection) {
        // make sure if data haven't changed
        assertNotNull(majorSection);
        assertFalse(majorSection.childSessions.isEmpty());
        assertTrue(majorSection.parents.isEmpty());
        assertEquals("Секция 2. Развитие атомной энергетики", majorSection.name);

        // check third (second level section)
        secondLevelSectionCheck(majorSection.childSessions.get(2));
    }

    private void secondLevelSectionCheck(Section section) {
        // make sure if data haven't changed
        assertNotNull(section);
        assertFalse(section.childSessions.isEmpty());
        assertTrue(section.parents.isEmpty());
        assertEquals("2.3 Ввод в эксплуатацию новых энергоблоков АЭС", section.name);

        // check date
        Date date = section.endTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(2018, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.MAY, calendar.get(Calendar.MONTH));
    }

    private void deepCheck(ConferenceRoot root) {
        // make sure if data haven't changed
        Section section = root.majorSections.get(0)
                .childSessions.get(0)
                .childSessions.get(0)
                .childSessions.get(0);

        assertNotNull(section);
        assertEquals("Деятельность УВВЭР в перспективе развития атомной энергетики", section.name);
    }
}
