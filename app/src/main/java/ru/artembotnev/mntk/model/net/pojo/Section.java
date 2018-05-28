package ru.artembotnev.mntk.model.net.pojo;

import java.util.Date;
import java.util.List;

/**
 * POJO class match section of json
 * <p>
 * Created by Artem Botnev on 20.05.2018
 */
public class Section {
    public String id;
    public String name;

    public Date startTime;
    public Date endTime;
    public long startTimeMs;
    public long endTimeMs;

    public String subLocationId;
    public String subLocationName;
    public String chairmanId;
    public String speakerId;
    public String sessionType;
    public String description;

    public List<Section> parents;
    public List<Section> childSessions;
}
