package actions;

import com.opencampus.automation.apis.ProfileApi;
import com.opencampus.automation.apis.Terminal3Api;
import com.opencampus.automation.bases.BaseApiAction;
import com.opencampus.automation.core.UserContext;
import io.restassured.http.Cookies;

public class LoginAction extends BaseApiAction {

    public LoginAction(UserContext userContext) {
        super(userContext);
    }

    public void loginUser() {
        if (!userContext.getUser().isLogin()) {
            String accessCode = getApi(Terminal3Api.class).getUserAccessCode(userContext.getUser().getUserToken());
            Cookies loginCookies = getApi(ProfileApi.class).getLoginCookies(accessCode);
            userContext.getUser().setLoginCookies(loginCookies);
        }
    }
}
