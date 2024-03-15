package steps;

import actions.QuestsAction;
import com.opencampus.automation.bases.BaseStep;
import com.opencampus.automation.dtos.LinkedXProfileDto;
import com.opencampus.automation.dtos.QuestDto;
import com.opencampus.automation.enums.QUEST_TYPE;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class OCQuestsStep extends BaseStep {

    @And("Verify user's OC Points should equal to total score of completed quests")
    public void verify_oc_points_equal_completed_quests() {
        int userOCPoint = use(QuestsAction.class).getOCPoint();
        int totalScoreOfCompletedQuests = use(QuestsAction.class).getTotalScoreOfCompletedQuests();
        Assert.assertEquals(userOCPoint, totalScoreOfCompletedQuests);
    }

    @And("Verify user can view all the quests")
    public void verify_user_can_view_quests() {
        List<QuestDto> quests = use(QuestsAction.class).getAllQuests();
        SoftAssert softAssert = new SoftAssert();
        for (QuestDto quest : quests) {
            softAssert.assertNotNull(quest.getId());
            softAssert.assertNotNull(quest.getPoints());
            softAssert.assertNotNull(quest.getPoints_id());
            softAssert.assertNotNull(quest.getQuestId());
            softAssert.assertNotNull(quest.getStatus());
        }
        softAssert.assertAll();
    }

    @And("Verify user has linked his X profile")
    public void verfiy_user_linked_x_profile() {
        LinkedXProfileDto xProfile = use(QuestsAction.class).getLinkedXProfile();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(xProfile.getId());
        softAssert.assertNotNull(xProfile.getName());
        softAssert.assertNotNull(xProfile.getProfile_image_url());
        softAssert.assertNotNull(xProfile.getUsername());
        softAssert.assertAll();
    }

    @And("Verify completed quests have the same linked X profile")
    public void verfiy_completed_quests_have_x_profile() {
        List<QuestDto> completedQuests = use(QuestsAction.class).getCompletedQuests();
        LinkedXProfileDto xProfile = use(QuestsAction.class).getLinkedXProfile();
        SoftAssert softAssert = new SoftAssert();
        for (QuestDto quest : completedQuests) {
            if (quest.getTwitterId() != null)
                softAssert.assertEquals(quest.getTwitterId(), xProfile.getId());
        }
        softAssert.assertAll();
    }

    @And("User OC Point should be 0 when there is no completed quest")
    public void OC_Point_0_when_no_completed_quest() {
        int userOCPoint = use(QuestsAction.class).getOCPoint();
        Assert.assertEquals(userOCPoint, 0);
    }

    @And("User can earn points by completing {word} quest")
    public void earn_points_by_complete_quest(String questType) {
        QUEST_TYPE qType = QUEST_TYPE.valueOf(questType);
        int pointBefore = use(QuestsAction.class).getOCPoint();
        use(QuestsAction.class).completeQuest(qType);
        int pointAfter = use(QuestsAction.class).getOCPoint();
        Assert.assertEquals(pointAfter, pointBefore + qType.questPoint);
    }

    @And("User cannot earn points by completing {word} quest again")
    public void cannot_earn_points_by_complete_quest_again(String questType) {
        QUEST_TYPE qType = QUEST_TYPE.valueOf(questType);
        int pointBefore = use(QuestsAction.class).getOCPoint();
        use(QuestsAction.class).completeQuestAgain(qType);
        int pointAfter = use(QuestsAction.class).getOCPoint();
        Assert.assertEquals(pointAfter, pointBefore);
    }

    @And("Verify user cannot complete a quest with invalid quest type")
    public void cannot_complete_invalid_quest_type() {
        Response res = use(QuestsAction.class).completeQuestWithError("invalidType", null);
        Assert.assertEquals(res.getStatusCode(), 400);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "invalid quest id");
    }

    @And("Verify user cannot complete TWITTER_POST_ABOUT_OCID quest with invalid post url")
    public void cannot_complete_x_post_with_invalid_data() {
        Response res = use(QuestsAction.class).completeQuestWithError(QUEST_TYPE.TWITTER_POST_ABOUT_OCID.name().toLowerCase(), "invalidPostUrl");
        Assert.assertEquals(res.getStatusCode(), 400);
    }

    @And("Verify User cannot complete {word} quest due to used X profile")
    public void cannot_complete_quest_due_to_used_x_profile(String questType) {
        QUEST_TYPE qType = QUEST_TYPE.valueOf(questType);
        Response res;
        if (qType == QUEST_TYPE.TWITTER_POST_ABOUT_OCID)
            res = use(QuestsAction.class).completeQuestWithError(qType.name().toLowerCase(), "https://twitter.com/thangatran/status/1768197035860361354");
        else
            res = use(QuestsAction.class).completeQuestWithError(qType.name().toLowerCase(), null);
        Assert.assertEquals(res.getStatusCode(), 400);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "duplicate quest attempt");
    }

    @And("Verify user cannot view quests with null login cookie")
    public void unauthorized_view_quests_with_null_login_cookies() {
        Response res = use(QuestsAction.class).getQuestWithError();
        Assert.assertEquals(res.getStatusCode(), 401);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "user is not authenticated");
    }

    @And("Verify user cannot view OC Points with null login cookie")
    public void unauthorized_view_OC_Balance_with_null_login_cookies() {
        Response res = use(QuestsAction.class).getOCPointWithError();
        Assert.assertEquals(res.getStatusCode(), 401);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "user is not authenticated");
    }

    @And("Verify user cannot view their linked X profile with null login cookie")
    public void unauthorized_view_linked_X_profile_with_null_login_cookies() {
        Response res = use(QuestsAction.class).getLinkedXProfileWithError();
        Assert.assertEquals(res.getStatusCode(), 401);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "user is not authenticated");
    }

    @And("Verify user cannot complete a quest with null login cookie")
    public void unauthorized_complete_quest_with_null_login_cookies() {
        Response res = use(QuestsAction.class).completeQuestWithError();
        Assert.assertEquals(res.getStatusCode(), 401);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "user is not authenticated");
    }
}
