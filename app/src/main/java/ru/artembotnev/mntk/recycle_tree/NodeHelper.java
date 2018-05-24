package ru.artembotnev.mntk.recycle_tree;

import java.util.ArrayList;
import java.util.List;

import ru.artembotnev.mntk.model.net.pojo.Section;
import tellh.com.recyclertreeview_lib.TreeNode;

/**
 * Helper for working with nodes of RecyclerTreeView
 * <p>
 * Created by Artem Botnev on 23.05.18
 */
public class NodeHelper {
    // cache capacity need for recycler view holders save its state
    private static int cacheCapacity;

    /**
     * builds TreeNode list from Section list
     *
     * @param sections obtained sections
     * @return TreeNose list
     */
    public static List<TreeNode> buildNodes(List<Section> sections) {
        List<TreeNode> nodes = new ArrayList<>();

        for (Section s : sections) {
            cacheCapacity++;
            nodes.add(createNode(s));
        }

        return nodes;
    }

    public static int getCacheCapacity() {
        return cacheCapacity;
    }

    private static TreeNode createNode(Section s) {
        cacheCapacity++;
        if (s.childSessions.isEmpty()) {
            // then crete child section node
            return new TreeNode<>(new ChildSection(s));
        } else {
            // then crete parent section node
            TreeNode<ParentSection> node = new TreeNode<>(new ParentSection(s));
            for (Section child : s.childSessions) {
                // add child of current level node
                TreeNode childNode = createNode(child);
                node.addChild(childNode);
            }
            return node;
        }
    }
}
