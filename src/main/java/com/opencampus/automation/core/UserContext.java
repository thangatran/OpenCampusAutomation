package com.opencampus.automation.core;

import com.opencampus.automation.bases.BaseApiAction;
import com.opencampus.automation.bases.BaseRestful;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class UserContext {

    @Getter
    private final OCUser user;
    private final ContextObject contextObject;
    private final Map<String, Object> dataSaver;

    public UserContext(OCUser user) {
        this.user = user;
        this.contextObject = new ContextObject();
        this.dataSaver = new HashMap<>();
    }

    public void saveData(String name, Object value) {
        this.dataSaver.put(name, value);
    }

    public Object getData(String name) {
        return this.dataSaver.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseApiAction> T use(Class<T> apiName) {
        return (T) this.contextObject.getObjectInstance(apiName, this);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseRestful> T getClient(Class<T> clientName) {
        return (T) this.contextObject.getObjectInstance(clientName);
    }

}
