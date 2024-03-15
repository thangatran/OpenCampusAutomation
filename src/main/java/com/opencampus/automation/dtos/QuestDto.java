package com.opencampus.automation.dtos;

import com.opencampus.automation.bases.BaseDto;
import lombok.Data;

@Data
public class QuestDto extends BaseDto {
    private String id;
    private String status;
    private String questId;
    private Integer points;
    private String points_id;
    private String twitterId;
}
