package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Const;

/**
 * 装饰节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
public abstract class Decorator extends BaseNode implements IDecorator {
    private BaseNode child;

    /** {@inheritDoc} */
    @Override
    public void setChild(BaseNode child) {

        this.child = child;
    }

    /** {@inheritDoc} */
    @Override
    public BaseNode getChild() {
        return this.child;
    }

    /** {@inheritDoc} */
    @Override
    public String getCategory() {
        return B3Const.DECORATOR;
    }

}
