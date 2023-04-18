package com.github.silencesu.behavior3java.core;

/**
 * 装饰节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
public interface IDecorator {

    /**
     * <p>setChild.</p>
     *
     * @param child a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    void  setChild(BaseNode child);

    /**
     * <p>getChild.</p>
     *
     * @return a {@link com.github.silencesu.behavior3java.core.BaseNode} object.
     */
    BaseNode getChild();


}
