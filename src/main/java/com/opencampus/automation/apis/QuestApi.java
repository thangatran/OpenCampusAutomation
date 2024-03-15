package com.opencampus.automation.apis;

import com.opencampus.automation.bases.BaseRestful;
import com.opencampus.automation.core.ConfigurationProperties;
import com.opencampus.automation.dtos.LinkedXProfileDto;
import com.opencampus.automation.dtos.QuestDto;
import com.opencampus.automation.enums.ENV_KEY;
import com.opencampus.automation.enums.QUEST_TYPE;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestApi extends BaseRestful {

    private static final String QUESTS_ENDPOINT = "quests";
    private static final String QUESTS_COMPLETE_ENDPOINT = "quests/complete";
    private static final String TWITTER_PROFILE_ENDPOINT = "twitter/profile";

    public QuestApi(){
        super(ConfigurationProperties.getEnvProp(ENV_KEY.QUEST_HOST));
    }

    public List<QuestDto> getCompletedQuests(Cookies userLoginCookies){
        Response res = get(QUESTS_ENDPOINT, userLoginCookies);
        Assert.assertEquals(res.getStatusCode(),200);
        List<QuestDto> completedQuests = new ArrayList<>();
        for(QUEST_TYPE questType : QUEST_TYPE.values()){
            completedQuests.addAll(res.jsonPath().getList(questType.name().toLowerCase() + ".findAll{it.status=='COMPLETED'}", QuestDto.class));
        }
        return completedQuests;
    }

    public List<QuestDto> getAllQuests(Cookies userLoginCookies){
        Response res = get(QUESTS_ENDPOINT, userLoginCookies);
        Assert.assertEquals(res.getStatusCode(),200);
        List<QuestDto> quests = new ArrayList<>();
        for(QUEST_TYPE questType : QUEST_TYPE.values()){
            quests.addAll(res.jsonPath().getList(questType.name().toLowerCase(), QuestDto.class));
        }
        return quests;
    }

    public LinkedXProfileDto getLinkedXProfile(Cookies userLoginCookies){
        Response res = get(TWITTER_PROFILE_ENDPOINT, userLoginCookies);
        return res.jsonPath().getObject("", LinkedXProfileDto.class);
    }

    public void completeQuest(Cookies userLoginCookies, String tweetUrl, String questType){
        Map<String,Object> payload = new HashMap<>();
        Map<String,String> data = new HashMap<>();
        if(tweetUrl != null){
            data.put("tweetUrl",tweetUrl);
        }
        payload.put("data", data);
        payload.put("questType", questType);
        Response res = post(QUESTS_COMPLETE_ENDPOINT, payload, userLoginCookies);
        Assert.assertEquals(res.getStatusCode(),200);
    }

    public void completeQuestAgain(Cookies userLoginCookies, String tweetUrl, String questType){
        Map<String,Object> payload = new HashMap<>();
        Map<String,String> data = new HashMap<>();
        if(tweetUrl != null){
            data.put("tweetUrl",tweetUrl);
        }
        payload.put("data", data);
        payload.put("questType", questType);
        Response res = post(QUESTS_COMPLETE_ENDPOINT, payload, userLoginCookies);
        Assert.assertEquals(res.getStatusCode(),400);
        Assert.assertEquals(res.jsonPath().getString("error.message"), "duplicate quest attempt");
    }

    public Response completeQuestWithError(Cookies userLoginCookies, String tweetUrl, String questType){
        Map<String,Object> payload = new HashMap<>();
        Map<String,String> data = new HashMap<>();
        if(tweetUrl != null){
            data.put("tweetUrl",tweetUrl);
        }
        payload.put("data", data);
        payload.put("questType", questType);
        return post(QUESTS_COMPLETE_ENDPOINT, payload, userLoginCookies);
    }

    public Response getQuestWithError(Cookies userLoginCookies){
        return get(QUESTS_ENDPOINT, userLoginCookies);
    }

    public Response getLinkedXProfileWithError(Cookies userLoginCookies){
        return get(TWITTER_PROFILE_ENDPOINT, userLoginCookies);
    }
}
