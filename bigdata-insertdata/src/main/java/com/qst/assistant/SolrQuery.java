package com.qst.assistant;

import java.util.List;

/**
 * Created by memory_fu on 2018/12/19.
 */
public class SolrQuery {

    private String collectionName;
    private String orderField;
    private String order;
    private int pageNum;
    private int pageSize;
    private List<Query> params;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Query> getParams() {
        return params;
    }

    public void setParams(List<Query> params) {
        this.params = params;
    }
}
