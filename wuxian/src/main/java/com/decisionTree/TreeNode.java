package com.decisionTree;

import java.util.LinkedHashSet;

public class TreeNode {
    String element;  //该值为数据的属性名称

    String value;    //上一个分裂属性在此结点的值

    LinkedHashSet<TreeNode> childs;  //结点的子结点，以有顺序的链式哈希集存储

    public TreeNode() {

        this.element = null;

        this.value = null;

        this.childs = null;

    }

    public TreeNode(String value) {

        this.element = null;

        this.value = value;

        this.childs = null;

    }

    public String getElement() {

        return this.element;

    }

    public void setElement(String e) {

        this.element = e;

    }

    public String getValue() {

        return this.value;

    }

    public void setValue(String v) {

        this.value = v;

    }

    public LinkedHashSet<TreeNode> getChilds() {

        return this.childs;

    }

    public void setChilds(LinkedHashSet<TreeNode> childs) {

        this.childs = childs;

    }
}
