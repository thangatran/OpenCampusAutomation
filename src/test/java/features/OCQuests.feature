@api_regression @OC_Quests
Feature: Open Campus Quests API

  Scenario: Verify user can view correct OC points, quests, linked X profile
    Given User logs in Open Campus
    Then Verify user can view all the quests
    And Verify user's OC Points should equal to total score of completed quests
    And Verify user has linked his X profile
    And Verify completed quests have the same linked X profile

  #note that this scenario requires a fresh signed up user e.g no quests have been done
  Scenario: Verify user can complete all quests and receive correct quest's points
    Given User logs in Open Campus
    Then User OC Point should be 0 when there is no completed quest
    And User can earn points by completing TWITTER_CONNECT quest
    And User can earn points by completing TWITTER_FOLLOW_JAY quest
    And User can earn points by completing TWITTER_FOLLOW_MNFT quest
    And User can earn points by completing TWITTER_FOLLOW_OC quest
    And User can earn points by completing TWITTER_FOLLOW_TT quest
    And User can earn points by completing TWITTER_RETWEET_OCD quest
    And User can earn points by completing TWITTER_RETWEET_OCT quest
    And User can earn points by completing TWITTER_POST_ABOUT_OCID quest
    And User can earn points by completing REFERRAL_INVITE_FRIENDS quest

  @OC_Quests_negative
  Scenario: Verify user can not complete a quest and earn points more than 1 time
    Given User logs in Open Campus
    Then User cannot earn points by completing TWITTER_CONNECT quest again
    And User cannot earn points by completing TWITTER_FOLLOW_JAY quest again
    And User cannot earn points by completing TWITTER_FOLLOW_MNFT quest again
    And User cannot earn points by completing TWITTER_FOLLOW_OC quest again
    And User cannot earn points by completing TWITTER_FOLLOW_TT quest again
    And User cannot earn points by completing TWITTER_RETWEET_OCD quest again
    And User cannot earn points by completing TWITTER_RETWEET_OCT quest again
    And User cannot earn points by completing TWITTER_POST_ABOUT_OCID quest again

  @OC_Quests_negative
  Scenario: Verify user cannot complete an undefined quest
    Given User logs in Open Campus
    Then Verify user cannot complete a quest with invalid quest type
    And Verify user cannot complete TWITTER_POST_ABOUT_OCID quest with invalid post url

  @OC_Quests_negative
  Scenario: Verify user whose linked X profile is used by another user cannot complete any quest
    Given User with used linked X profile logs in Open Campus
    Then Verify User cannot complete TWITTER_CONNECT quest due to used X profile
    And Verify User cannot complete TWITTER_FOLLOW_JAY quest due to used X profile
    And Verify User cannot complete TWITTER_FOLLOW_MNFT quest due to used X profile
    And Verify User cannot complete TWITTER_FOLLOW_OC quest due to used X profile
    And Verify User cannot complete TWITTER_FOLLOW_TT quest due to used X profile
    And Verify User cannot complete TWITTER_RETWEET_OCD quest due to used X profile
    And Verify User cannot complete TWITTER_RETWEET_OCT quest due to used X profile
    And Verify User cannot complete TWITTER_POST_ABOUT_OCID quest due to used X profile

  @OC_Quests_negative
  Scenario: Verify unauthorized error when calling quest apis with bad token
    Given User logs in Open Campus
    Then Verify user cannot view quests with null login cookie
    And Verify user cannot view OC Points with null login cookie
    And Verify user cannot view their linked X profile with null login cookie
    And Verify user cannot complete a quest with null login cookie
