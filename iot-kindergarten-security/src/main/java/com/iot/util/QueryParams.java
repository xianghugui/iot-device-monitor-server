package com.iot.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页类
 */
@Getter
@Setter
public class QueryParams {
    private int page = 0;
    private int limit = 20;
    private Map<String, Object> params = new HashMap<>();

    public QueryParams(){}

    public QueryParams(Map<String, Object> params) {
        if (params.containsKey("page")) {
            this.page = Integer.parseInt(params.get("page").toString());
            params.remove("page");
        }
        if (params.containsKey("limit")) {
            this.limit = Integer.parseInt(params.get("limit").toString());
            params.remove("limit");
        }
        this.params.putAll(params);
    }
}
