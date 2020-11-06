package com.ry.erp.sys.common;

import com.alibaba.fastjson.JSON;


/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */

public class CacheBean {
    private String key;

    private Object value;

    public CacheBean(){

    }

    public CacheBean(String key, Object value) {
        super();
        this.key=key;
        this.value=value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return JSON.toJSON(value).toString();
    }

    public void setValue(Object value) {
        this.value = value;
    }



}
