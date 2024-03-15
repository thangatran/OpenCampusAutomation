package com.opencampus.automation.bases;

import com.opencampus.automation.core.ContextManager;
import com.opencampus.automation.core.Global;
import com.opencampus.automation.core.OCUser;
import com.opencampus.automation.core.UserContext;

public class BaseStep {
    private final ContextManager contextManager;

    public BaseStep() {
        this.contextManager = Global.get(ContextManager.class);
    }

    public void createUser() {
        OCUser user = OCUser.getDefaultUser();
        this.contextManager.createUserContext("default user", user);
    }

    public void createUser(OCUser user) {
        this.contextManager.createUserContext("user", user);
    }

    private UserContext currentUserContext() {
        return this.contextManager.getCurrentUserContext();
    }

    protected <T extends BaseApiAction> T use(Class<T> apiName) {
        return currentUserContext().use(apiName);
    }

    protected void save(String name, Object value) {
        currentUserContext().saveData(name, value);
    }

    protected Object getSaved(String name) {
        return currentUserContext().getData(name);
    }
}
