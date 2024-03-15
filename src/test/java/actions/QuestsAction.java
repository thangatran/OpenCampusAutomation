package actions;

import com.opencampus.automation.apis.PointsApi;
import com.opencampus.automation.apis.QuestApi;
import com.opencampus.automation.bases.BaseApiAction;
import com.opencampus.automation.core.UserContext;
import com.opencampus.automation.dtos.LinkedXProfileDto;
import com.opencampus.automation.dtos.QuestDto;
import com.opencampus.automation.enums.QUEST_TYPE;
import io.restassured.response.Response;

import java.util.List;

public class QuestsAction extends BaseApiAction {
    public QuestsAction(UserContext userContext) {
        super(userContext);
    }

    public int getOCPoint() {
        return getApi(PointsApi.class).getOCPoints(userContext.getUser().getLoginCookies());
    }

    public int getTotalScoreOfCompletedQuests() {
        List<QuestDto> completedQuests = getApi(QuestApi.class).getCompletedQuests(userContext.getUser().getLoginCookies());
        int totalScore = 0;
        for (QuestDto quest : completedQuests)
            totalScore += quest.getPoints();
        return totalScore;
    }

    public List<QuestDto> getAllQuests() {
        return getApi(QuestApi.class).getAllQuests(userContext.getUser().getLoginCookies());
    }

    public List<QuestDto> getCompletedQuests() {
        return getApi(QuestApi.class).getCompletedQuests(userContext.getUser().getLoginCookies());
    }

    public LinkedXProfileDto getLinkedXProfile() {
        return getApi(QuestApi.class).getLinkedXProfile(userContext.getUser().getLoginCookies());
    }

    public void completeQuest(QUEST_TYPE questType) {
        if (questType == QUEST_TYPE.TWITTER_POST_ABOUT_OCID) {
            String postUrl = "https://twitter.com/thangatran/status/1768197035860361354";
            getApi(QuestApi.class).completeQuest(userContext.getUser().getLoginCookies(), postUrl, questType.name().toLowerCase());
        } else if (questType == QUEST_TYPE.REFERRAL_INVITE_FRIENDS) {
            //This would require implementation of signup flow for new user
            // To be implemented later
        } else {
            getApi(QuestApi.class).completeQuest(userContext.getUser().getLoginCookies(), null, questType.name().toLowerCase());
        }
    }

    public void completeQuestAgain(QUEST_TYPE questType) {
        if (questType == QUEST_TYPE.TWITTER_POST_ABOUT_OCID) {
            String postUrl = "https://twitter.com/thangatran/status/1768197035860361354";
            getApi(QuestApi.class).completeQuestAgain(userContext.getUser().getLoginCookies(), postUrl, questType.name().toLowerCase());
        } else {
            getApi(QuestApi.class).completeQuestAgain(userContext.getUser().getLoginCookies(), null, questType.name().toLowerCase());
        }
    }

    public Response completeQuestWithError(String questType, String postUrl) {
        return getApi(QuestApi.class).completeQuestWithError(userContext.getUser().getLoginCookies(), postUrl, questType);
    }

    public Response getQuestWithError() {
        return getApi(QuestApi.class).getQuestWithError(null);
    }

    public Response getOCPointWithError() {
        return getApi(PointsApi.class).getBalanceWithError(null);
    }

    public Response getLinkedXProfileWithError() {
        return getApi(QuestApi.class).getLinkedXProfileWithError(null);
    }

    public Response completeQuestWithError() {
        return getApi(QuestApi.class).completeQuestWithError(null, null, QUEST_TYPE.TWITTER_FOLLOW_OC.name().toLowerCase());
    }
}
