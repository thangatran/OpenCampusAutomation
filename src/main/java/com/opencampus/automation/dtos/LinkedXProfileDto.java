package com.opencampus.automation.dtos;

import com.opencampus.automation.bases.BaseDto;
import lombok.Data;

@Data
public class LinkedXProfileDto extends BaseDto {

    private String id;
    private String name;
    private String profile_image_url;
    private String username;
}
