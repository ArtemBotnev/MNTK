package ru.artembotnev.mntk.recycle_tree;

import ru.artembotnev.mntk.R;
import ru.artembotnev.mntk.model.net.pojo.Section;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Class which reflects pojo class Sectionand doesn't have children,
 * to work with tree RecyclerView
 * <p>
 * Created by Artem Botnev on 22.05.18
 */
public class ParentSection implements LayoutItemType {
    String name;

    public ParentSection(Section section) {
        name = section.name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.parent_layout;
    }
}
