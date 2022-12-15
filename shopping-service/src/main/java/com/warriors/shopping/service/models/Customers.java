package com.warriors.shopping.service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customers {
    private Long customerId;
    private String numberID;
    private String firtsName;
    private String lastName;
    private String email;
    private Region region;
    private String state;
    private String photoUrl;
}
