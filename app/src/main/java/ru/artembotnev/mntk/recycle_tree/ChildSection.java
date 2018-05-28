package ru.artembotnev.mntk.recycle_tree;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.artembotnev.mntk.R;
import ru.artembotnev.mntk.model.net.pojo.Section;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Class which reflects pojo class Section and doesn't have children,
 * to work with tree RecyclerView
 * <p>
 * Created by Artem Botnev on 22.05.18
 */
public class ChildSection implements LayoutItemType {
    private String name;
    private String time;
    private String date;
    private String type;

    public ChildSection(Section section) {
        name = section.name;
        time = getTime(section.startTime, section.endTime);
        date = getDate(section.startTime);
        type = section.sessionType;
    }

    @Override
    public int getLayoutId() {
        return R.layout.section_card;
    }

    /**
     * creates date string
     *
     * @param date of section
     * @return day month day of week
     */
    private String getDate(Date date) {
        SimpleDateFormat df =
                new SimpleDateFormat("dd MMM EE", Locale.getDefault());

        return df.format(date);
    }

    /**
     * creates time string
     *
     * @param startDate of section
     * @param endDate   of section
     * @return section hour:minute
     */
    private String getTime(Date startDate, Date endDate) {
        SimpleDateFormat df =
                new SimpleDateFormat("HH:mm", Locale.getDefault());

        return String.format("%s - %s", df.format(startDate), df.format(endDate));
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
