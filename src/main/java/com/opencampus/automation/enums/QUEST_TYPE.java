package com.opencampus.automation.enums;

public enum QUEST_TYPE {

    REFERRAL_INVITE_FRIENDS(1000),
    TWITTER_CONNECT(100),
    TWITTER_FOLLOW_JAY(100),
    TWITTER_FOLLOW_MNFT(100),
    TWITTER_FOLLOW_OC(100),
    TWITTER_FOLLOW_TT(100),
    TWITTER_POST_ABOUT_OCID(1000),
    TWITTER_RETWEET_OCD(100),
    TWITTER_RETWEET_OCT(100);

    public final int questPoint;

    QUEST_TYPE(int questPoint) {
        this.questPoint = questPoint;
    }
}
