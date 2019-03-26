package com.video.bigdata.bigdatasolr.service;

/**
 * ${description}
 * Created By liwen
 */
public interface ISolrService {

    public String query(String paramsJson);

    public String queryByGroup(String paramsJson);

    public String queryByCollections(String paramsJson);

    public String addIndex(String paramsJson);

    public String deleteIndex(String paramsJson);

    public String updateIndex(String paramsJson);

    public String addDataAndIndex(String paramsJson);

    public String deleteDataAndIndex(String paramsJson);

    public String updateDataAndIndex(String paramsJson);

    public Long count(String paramsJson);

    public Long countByCondition(String paramsJson);

    public String imageSearchData(String paramsJson);
}
