package com.opencampus.automation.core;

import com.opencampus.automation.enums.ENV_KEY;
import io.restassured.http.Cookies;
import lombok.Getter;
import lombok.Setter;

public class OCUser {

    @Getter
    private final String userToken;
    @Setter
    private Cookies loginCookies;

    public OCUser(String userToken) {
        this.userToken = userToken;
        this.loginCookies = null;
    }

    public static OCUser getDefaultUser() {
        String userToken = ConfigurationProperties.getEnvProp(ENV_KEY.USER_TOKEN);
        return new OCUser(userToken);
    }

    public Boolean isLogin() {
        return loginCookies != null;
    }

    public Cookies getLoginCookies() {
        if (!isLogin())
            throw new RuntimeException("User has not logged in yet. Please login first");
        return loginCookies;
    }
}