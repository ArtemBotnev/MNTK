package ru.artembotnev.mntk.recycle_tree;

import android.os.Build;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.artembotnev.mntk.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Node binder for section nodes which have children
 * <p>
 * Created by Artem Botnev on 22.05.18
 */
public class SectionNodeBinder extends TreeViewBinder<SectionNodeBinder.SectionHolder> {
    @Override
    public SectionHolder provideViewHolder(View view) {
        return new SectionHolder(view);
    }

    @Override
    public void bindView(SectionHolder holder, int position, TreeNode node) {
        ParentSection section = (ParentSection) node.getContent();
        holder.bind(section.name, node.isRoot());
    }

    @Override
    public int getLayoutId() {
        return R.layout.parent_layout;
    }

    /**
     * View holder for sections which have children
     */
    public class SectionHolder extends TreeViewBinder.ViewHolder {
        private ImageView arrowImage;
        private TextView nameView;

        SectionHolder(View rootView) {
            super(rootView);
            arrowImage = itemView.findViewById(R.id.arrow);
            nameView = itemView.findViewById(R.id.name);
        }

        void bind(String name, boolean isRoot) {
            if (isRoot) {
                // make text bigger for root element
                TextViewCompat.setTextAppearance(nameView, R.style.Big);
            }
            nameView.setText(name);
        }

        public ImageView getImage() {
            return arrowImage;
        }
    }
}
