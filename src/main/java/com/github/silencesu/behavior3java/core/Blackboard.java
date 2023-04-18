package com.github.silencesu.behavior3java.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 黑板报
 * k-v存储数据
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
@Data
public class Blackboard {


    //    private Memory baseMemory;
    private Map<String, TreeMemory> treeMemory = new HashMap<>();


    TreeData getTreeData(String treeScope) {

        if (treeMemory.get(treeScope) == null) {
            return new TreeData();
        }

        return treeMemory.get(treeScope).getTreeData();

    }


    void SetTree(String key, Object object, String treeScope) {


        Memory memory = this.getMemeory(treeScope, "");

        memory.getMemeory().put(key, object);
    }

    private TreeMemory getTreeMemory(String treeScope) {

        TreeMemory tm = treeMemory.get(treeScope);
        if (tm == null) {
            tm = new TreeMemory();
            treeMemory.put(treeScope, tm);
        }

        return tm;


    }

    /**
     * <p>getMemeory.</p>
     *
     * @param treeScope a {@link java.lang.String} object.
     * @param nodeScope a {@link java.lang.String} object.
     * @return a {@link com.github.silencesu.behavior3java.core.Blackboard.Memory} object.
     */
    public Memory getMemeory(String treeScope, String nodeScope) {

        TreeMemory tm = getTreeMemory(treeScope);


        return getNodeMemory(tm, nodeScope);
    }


    Boolean getBool(String key, String treeScope, String nodeScope) {

        Memory memory = getMemeory(treeScope, nodeScope);
        if (memory == null) {
            return false;
        }
        Object object = memory.getMemeory().get(key);
        if (object == null) {
            return false;
        }
        return (Boolean) object;
    }


    private Memory getNodeMemory(TreeMemory treeMemory, String nodeScope) {

        Memory memory = treeMemory.getNodeMemory().get(nodeScope);

        if (memory == null) {

            memory = new Memory();
            treeMemory.getNodeMemory().put(nodeScope, memory);

        }
        return memory;


    }


    /**
     * <p>setParam.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     * @param treeScope a {@link java.lang.String} object.
     * @param nodeScope a {@link java.lang.String} object.
     */
    public void setParam(String key, Object value, String treeScope, String nodeScope) {
        Memory memory = getMemeory(treeScope, nodeScope);
        memory.getMemeory().put(key, value);
    }


    /**
     * <p>getParam.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param treeScope a {@link java.lang.String} object.
     * @param nodeScope a {@link java.lang.String} object.
     * @param <T> a T object.
     * @return a T object.
     */
    @SuppressWarnings("unchecked")
    public <T> T getParam(String key, String treeScope, String nodeScope) {
        Memory memory = getMemeory(treeScope, nodeScope);
        Object object = memory.getMemeory().get(key);
        return (T) object;
    }

    @Data
    class TreeData {

        Memory nodeMemory = new Memory();
        List<BaseNode> openNodes = new ArrayList<>();
        private int traversalDepth;
        private int traversalCycle;


    }

    @Data
    public class Memory {
        private Map<String, Object> memeory = new HashMap<>();

        public Object get(String key) {
            return memeory.get(key);
        }
    }

    @Data
    public class TreeMemory {
        private TreeData treeData = new TreeData();
        private Map<String, Memory> nodeMemory = new HashMap<>();

    }


}
