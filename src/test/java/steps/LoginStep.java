package steps;

import actions.LoginAction;
import com.opencampus.automation.bases.BaseStep;
import com.opencampus.automation.core.ConfigurationProperties;
import com.opencampus.automation.core.OCUser;
import com.opencampus.automation.enums.ENV_KEY;
import io.cucumber.java.en.And;

public class LoginStep extends BaseStep {

    @And("User logs in Open Campus")
    public void login_open_campus() {
        createUser();
        use(LoginAction.class).loginUser();
    }

    @And("User with used linked X profile logs in Open Campus")
    public void user_with_used_x_profile_logs_in() {
        createUser(new OCUser(ConfigurationProperties.getEnvProp(ENV_KEY.USER_WITH_SAME_X_TOKEN)));
        use(LoginAction.class).loginUser();
    }

}
