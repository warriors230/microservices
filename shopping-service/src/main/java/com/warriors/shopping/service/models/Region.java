package com.warriors.shopping.service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Region {
    private Long regionID;
    private String name;
}
