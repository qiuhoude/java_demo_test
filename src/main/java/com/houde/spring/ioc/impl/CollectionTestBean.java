package com.houde.spring.ioc.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class CollectionTestBean {

    private Map<String, String> maps;
    private Set<String> values;

    public void setValues(Set<String> values) {
        this.values = values;
    }

    public Set<String> getValues() {
        return values;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }
}
