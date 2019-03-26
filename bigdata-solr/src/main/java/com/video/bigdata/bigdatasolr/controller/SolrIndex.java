package com.video.bigdata.bigdatasolr.controller;


import com.video.bigdata.bigdatasolr.service.ISolrService;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SolrIndex {

    private static final Logger log = LoggerFactory.getLogger(SLF4JLogger.class);
    @Autowired
    private ISolrService solrService;

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public String query(@RequestBody String paramsJson){
        return solrService.query(paramsJson);
    }
    @RequestMapping(value = "/queryByGroup",method = RequestMethod.POST)
    @ResponseBody
    public String queryByGroup(@RequestBody String paramsJson){
        return solrService.queryByGroup(paramsJson);
    }
    @RequestMapping(value = "/queryByCollections",method = RequestMethod.POST)
    @ResponseBody
    public String queryByCollections(@RequestBody String paramsJson){
        return solrService.queryByCollections(paramsJson);
    }

    @RequestMapping(value = "/addDataAndIndex",method = RequestMethod.POST)
    @ResponseBody
    public String addDataAndIndex(@RequestBody String paramsJson){ return solrService.addDataAndIndex(paramsJson); }

    @RequestMapping(value = "/deleteDataAndIndex",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDataAndIndex(@RequestBody String paramsJson) {return solrService.deleteDataAndIndex(paramsJson);}

    @RequestMapping(value = "/updateDataAndIndex",method = RequestMethod.POST)
    @ResponseBody
    public String updateDataAndIndex(@RequestBody String paramsJson){return  solrService.updateDataAndIndex(paramsJson);}

    @RequestMapping(value = "/countByCondition",method = RequestMethod.POST)
    @ResponseBody
    public Long countByCondition(String paramsJson){
        return solrService.countByCondition(paramsJson);
    }

    @RequestMapping(value = "/count",method = RequestMethod.POST)
    @ResponseBody
    public Long count(String paramsJson){
        return solrService.count(paramsJson);
    }

    @RequestMapping(value = "/imageSearchData",method = RequestMethod.POST)
    @ResponseBody
    public String imageSearchData(String paramsJson){
        return solrService.imageSearchData(paramsJson);
    }
}
