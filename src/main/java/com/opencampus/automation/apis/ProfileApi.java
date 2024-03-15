package com.opencampus.automation.apis;

import com.opencampus.automation.bases.BaseRestful;
import com.opencampus.automation.core.ConfigurationProperties;
import com.opencampus.automation.enums.ENV_KEY;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class ProfileApi extends BaseRestful {

    private static final String SSO_LOGIN_ENDPOINT = "sso/login";

    public ProfileApi() {
        super(ConfigurationProperties.getEnvProp(ENV_KEY.PROFILE_HOST));
    }

    public Cookies getLoginCookies(String accessCode) {
        Map<String, String> payload = new HashMap<>();
        payload.put("accessCode", accessCode);
        payload.put("redirectUrl", ConfigurationProperties.getEnvProp(ENV_KEY.DASHBOARD_URL));
        Response res = post(SSO_LOGIN_ENDPOINT, payload);
        Assert.assertEquals(res.getStatusCode(), 200);
        return res.getDetailedCookies();
    }
}
