package com.github.silencesu.behavior3java.core;

import java.util.List;

/**
 * 组合节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
public interface IComposite {

    /**
     * 子节点count
     *
     * @return a int.
     */
    int getChildCount();

    /**
     * 根据id 索引 子节点
     *
     * @param index a int.
     * @return a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    BaseNode getChild(int index);

    /**
     * 获取所有子节点
     *
     * @return a {@link java.util.List} object.
     * @since 1.1.1
     */
    List<BaseNode> getChildList();

    /**
     * 增加一个子节点
     *
     * @param baseNode a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    void addChild(BaseNode baseNode);

    /**
     * 打乱子节点
     *
     * @since 1.1.1
     */
    void shuffle();


}
