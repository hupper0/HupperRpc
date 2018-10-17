package com.nettytest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhp@meitu.com
 * @date 2018/9/5 下午4:35
 */
//@ToStr
public class Message implements Serializable {

    private String action;
    private Map<String, Object> fieldMap = new HashMap<>();

    public void set(MessageField field, Object o) {
        set(field.getName(), o);
    }

    private void set(String key, Object o) {
        fieldMap.put(key, o);
    }

    public Object get(MessageField field) {
        return get(field.getName());
    }

    public Object get(String key) {
        return fieldMap.get(key);
    }

    public Action getAction() {
        return Action.get(action);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setAction(Action action) {
        this.action = action.name();
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("action: " + this.action + "; [");
        for (Map.Entry<String, Object> e : fieldMap.entrySet()) {
            sb.append(e.toString()).append(" ");
        }
        sb.append("]");
        return sb.toString();

    }
}