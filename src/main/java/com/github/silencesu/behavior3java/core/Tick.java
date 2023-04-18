package com.github.silencesu.behavior3java.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Tick
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
@Data
public class Tick {

    private BehaviorTree tree;

    private Blackboard blackboard;

    private List<BaseNode> openNodes = new ArrayList<>();

    Object target;

    private int nodeCount;

    /**
     * <p>Constructor for Tick.</p>
     */
    public Tick() {
        initialize();

    }


    /**
     * <p>initialize.</p>
     */
    public void initialize() {

        this.tree = null;
        this.blackboard = null;


        this.openNodes = new ArrayList<>();
        this.nodeCount = 0;

    }

    /**
     * <p>treeId.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String treeId() {
        return this.getTree().getId();
    }


    /**
     * <p>Getter for the field <code>tree</code>.</p>
     *
     * @return a {@link com.github.silencesu.behavior3java.core.BehaviorTree} object.
     */
    public BehaviorTree getTree() {
        return this.tree;
    }


    /**
     * <p>Getter for the field <code>blackboard</code>.</p>
     *
     * @return a {@link com.github.silencesu.behavior3java.core.Blackboard} object.
     */
    public Blackboard getBlackboard() {
        return this.blackboard;
    }

    /**
     * <p>enterNode.</p>
     *
     * @param node a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    public void enterNode(BaseNode node) {
        this.nodeCount++;
        this.openNodes.add(node);
    }

    /**
     * <p>openNode.</p>
     *
     * @param node a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    public void openNode(BaseNode node) {

    }

    /**
     * <p>tickNode.</p>
     *
     * @param node a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    public void tickNode(BaseNode node) {

    }

    /**
     * <p>closeNode.</p>
     *
     * @param node a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    public void closeNode(BaseNode node) {

        if (this.openNodes.size() > 0) {
            this.openNodes.remove(node);

        }


    }

    /**
     * <p>exitNNode.</p>
     *
     * @param node a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    public void exitNNode(BaseNode node) {

    }


}
