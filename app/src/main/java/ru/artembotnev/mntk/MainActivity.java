package ru.artembotnev.mntk;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ru.artembotnev.mntk.model.DataManager;
import ru.artembotnev.mntk.model.net.pojo.ConferenceRoot;
import ru.artembotnev.mntk.model.net.pojo.Section;
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

        new AsyncDataLoading().execute();
    }

    private void updateUI(List<Section> sections) {
        List<TreeNode> nodes = NodeHelper.buildNodes(sections);
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

    /**
     * Inner class for async getting data from net
     */
    private class AsyncDataLoading extends AsyncTask<Void, Void, ConferenceRoot> {
        @Override
        protected ConferenceRoot doInBackground(Void... voids) {
            // getting ConferenceRoot data by DataManager
            try {
                return DataManager.getInstance().getConferenceRoot();
            } catch (IOException e) {
                e.printStackTrace();
                // try again
                new AsyncDataLoading().execute();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ConferenceRoot conferenceRoot) {
            if (conferenceRoot != null) {
                updateUI(conferenceRoot.majorSections);
            } else {
                Toast.makeText(MainActivity.this, R.string.data_not_obtained, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
