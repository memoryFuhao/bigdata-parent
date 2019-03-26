package com.qst.assistant;

import java.util.List;

/**
 * Created by memory_fu on 2018/12/19.
 */
public class Query {
    private String field;
    private String type;
    private List<String> value;

    public Query() {
    }

    public Query(String field, String type, List<String> value) {
        this.field = field;
        this.type = type;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
            this.value = value;
        }
}
