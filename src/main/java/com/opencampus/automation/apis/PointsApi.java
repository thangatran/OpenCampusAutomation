package com.opencampus.automation.apis;

import com.opencampus.automation.bases.BaseRestful;
import com.opencampus.automation.core.ConfigurationProperties;
import com.opencampus.automation.enums.ENV_KEY;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

public class PointsApi extends BaseRestful {

    public static final String BALANCE_ENDPOINT = "balance";

    public PointsApi() {
        super(ConfigurationProperties.getEnvProp(ENV_KEY.POINTS_HOST));
    }

    public int getOCPoints(Cookies userLoginCookies) {
        Response res = get(BALANCE_ENDPOINT, userLoginCookies);
        return res.jsonPath().getInt("balance");
    }

    public Response getBalanceWithError(Cookies userLoginCookies) {
        return get(BALANCE_ENDPOINT, userLoginCookies);
    }
}
