package ru.artembotnev.mntk.model.net.pojo;

import java.util.Date;
import java.util.List;

/**
 * POJO class match section of json
 * <p>
 * Created by Artem Botnev on 20.05.2018
 */
public class ConferenceRoot {
    public String id;
    public String name;
    public String type;

    public Date startDateTime;
    public Date endDateTime;
    public long startDateTimeMs;
    public long endDateTimeMs;

    public String locationId;
    public List<Section> majorSections;
}
