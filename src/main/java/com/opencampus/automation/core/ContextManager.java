package com.opencampus.automation.core;

import java.util.HashMap;
import java.util.Map;

public class ContextManager {
    private final Map<String, UserContext> userContexts;
    private UserContext currentUserContext;

    public ContextManager() {
        this.userContexts = new HashMap<>();
        this.currentUserContext = null;
    }

    public UserContext getCurrentUserContext() {
        if (this.currentUserContext == null)
            throw new RuntimeException("There is no current user context. Please create one with a user");
        return currentUserContext;
    }

    public void createUserContext(String contextName, OCUser user) {
        UserContext userContext = new UserContext(user);
        userContexts.put(contextName, userContext);
        this.currentUserContext = userContext;
    }

    public UserContext switchUserContext(String contextName) {
        UserContext userContext = userContexts.get(contextName);
        if (userContext == null)
            throw new RuntimeException("User context: " + contextName + " has not created yet");
        this.currentUserContext = userContext;
        return userContext;
    }
}
