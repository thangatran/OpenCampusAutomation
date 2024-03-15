package com.opencampus.automation.bases;

import com.opencampus.automation.core.UserContext;

public class BaseApiAction {

    protected UserContext userContext;

    protected BaseApiAction(UserContext context) {
        this.userContext = context;
    }

    protected <T extends BaseRestful> T getApi(Class<T> apiClassName) {
        return userContext.getClient(apiClassName);
    }
}
