package com.warriors.microservicios.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class ErrorMessages {
    private String code;
    private List<Map<String,String>>messages;
}
