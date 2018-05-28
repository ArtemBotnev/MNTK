package ru.artembotnev.mntk.recycle_tree;

import android.view.View;
import android.widget.TextView;

import ru.artembotnev.mntk.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Node binder for section nodes which don't have children
 * <p>
 * Created by Artem Botnev on 22.05.18
 */
public class ChildNodeBinder extends TreeViewBinder<ChildNodeBinder.ChildHolder> {

    @Override
    public ChildHolder provideViewHolder(View view) {
        return new ChildHolder(view);
    }

    @Override
    public void bindView(ChildHolder holder, int position, TreeNode node) {
        ChildSection section = (ChildSection) node.getContent();
        holder.bind(section);
    }

    @Override
    public int getLayoutId() {
        return R.layout.section_card;
    }

    /**
     * View holder for sections which don't have children
     */
    class ChildHolder extends TreeViewBinder.ViewHolder {

        TextView timeView;
        TextView dateView;
        TextView nameView;
        TextView typeView;

        ChildHolder(View rootView) {
            super(rootView);
            timeView = itemView.findViewById(R.id.time);
            dateView = itemView.findViewById(R.id.date);
            nameView = itemView.findViewById(R.id.title);
            typeView = itemView.findViewById(R.id.type);
        }

        void bind(ChildSection section) {
            timeView.setText(section.getTime());
            dateView.setText(section.getDate());
            nameView.setText(section.getName());
            typeView.setText(section.getType());
        }
    }
}
