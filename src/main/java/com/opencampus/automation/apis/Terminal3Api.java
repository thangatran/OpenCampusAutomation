package com.opencampus.automation.apis;

import com.opencampus.automation.bases.BaseRestful;
import com.opencampus.automation.core.ConfigurationProperties;
import com.opencampus.automation.enums.ENV_KEY;
import io.restassured.response.Response;
import org.testng.Assert;

public class Terminal3Api extends BaseRestful {

    private static final String AUTHORIZE_ENDPOINT = "v1/openidc/authorize?client_id=%s&redirect_uri=%s&response_type=code&scope=openid&state=t3&token=%s";

    public Terminal3Api() {
        super(ConfigurationProperties.getEnvProp(ENV_KEY.TERMINAL3_HOST));
    }

    public String getUserAccessCode(String userToken) {
        String clientId = ConfigurationProperties.getEnvProp(ENV_KEY.CLIENT_ID);
        String dashboardUrl = ConfigurationProperties.getEnvProp(ENV_KEY.DASHBOARD_URL);
        String requestUrl = String.format(AUTHORIZE_ENDPOINT, clientId, dashboardUrl, userToken);
        Response res = get(requestUrl, false);
        Assert.assertNotNull(res.getHeader("location"));
        return res.getHeader("location").split("\\?")[1].split("&")[0].split("=")[1];
    }
}
