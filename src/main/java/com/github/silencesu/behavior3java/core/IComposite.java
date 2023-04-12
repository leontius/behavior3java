package com.github.silencesu.behavior3java.core;

import java.util.List;

/**
 * 组合节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public interface IComposite {

    /**
     * 子节点count
     *
     * @return
     */
    int getChildCount();

    /**
     * 根据id 索引 子节点
     *
     * @param index
     * @return
     */
    BaseNode getChild(int index);

    /**
     * 获取所有子节点
     *
     * @return
     */
    List<BaseNode> getChildList();

    /**
     * 增加一个子节点
     *
     * @param baseNode
     */
    void addChild(BaseNode baseNode);

    /**
     * 打乱子节点
     *
     * @return
     */
    void shuffle();


}
