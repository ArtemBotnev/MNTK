package ru.artembotnev.mntk.model.net.events;

import java.util.List;

import ru.artembotnev.mntk.model.net.pojo.Section;

/**
 * Container for sections' list, creates when sections data are successfully obtained from net
 * <p>
 * Create by Artem Botnev 03.06.2018
 */
public class EventGetSectionFromNet {
    private List<Section> sections;

    public EventGetSectionFromNet(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }
}
