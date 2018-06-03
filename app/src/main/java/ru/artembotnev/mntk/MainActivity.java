package ru.artembotnev.mntk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import ru.artembotnev.mntk.model.net.events.EventGetSectionFromNet;
import ru.artembotnev.mntk.model.net.events.EventNetError;
import ru.artembotnev.mntk.recycle_tree.ChildNodeBinder;
import ru.artembotnev.mntk.recycle_tree.NodeHelper;
import ru.artembotnev.mntk.recycle_tree.SectionNodeBinder;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

/**
 * Main activity of app
 * <p>
 * Created by Artem Botnev on 20.05.18
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

//        new AsyncDataLoading().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // register in eventBus
        EventBus.getDefault().register(this);
        // get data about conference schedule
        MntkIntentService.obtainConferenceRoot(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // unregister in eventBus
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUI(EventGetSectionFromNet event) {
        List<TreeNode> nodes = NodeHelper.buildNodes(event.getSections());
        // expand item view count for saving holders state
        recycler.setItemViewCacheSize(NodeHelper.getCacheCapacity());

        // create adapter for recycler
        TreeViewAdapter adapter = new TreeViewAdapter(
                nodes, Arrays.asList(new SectionNodeBinder(), new ChildNodeBinder()));

        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    // update and toggle the node
                    onToggle(!node.isExpand(), holder);
                }

                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                SectionNodeBinder.SectionHolder sectionViewHolder =
                        (SectionNodeBinder.SectionHolder) holder;
                // add animation for image by click event
                ImageView arrow = sectionViewHolder.getImage();
                int rotateDegree = isExpand ? 90 : -90;
                arrow.animate()
                        .rotationBy(rotateDegree)
                        .start();

            }
        });

        recycler.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showNetworkError(EventNetError event) {
        Toast.makeText(this, R.string.data_not_obtained, Toast.LENGTH_SHORT).show();
    }
}
