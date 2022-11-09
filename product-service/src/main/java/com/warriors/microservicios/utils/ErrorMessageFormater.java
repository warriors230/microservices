package com.warriors.microservicios.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ErrorMessageFormater {

      public String formatMessage(BindingResult result){
         List<Map<String,String>>errors = result.getFieldErrors().stream()
                 .map(err -> {
                     Map<String,String> error = new HashMap<>();
                     error.put(err.getField(),err.getCode());
                     return error;
                 }).collect(Collectors.toList());
         ErrorMessages errorMessages = ErrorMessages.builder()
                 .code("01")
                 .messages(errors)
                 .build();
         ObjectMapper mapper = new ObjectMapper();
         String jsonString="";
         try {
             jsonString= mapper.writeValueAsString(errorMessages);
         }catch (JsonProcessingException e){
            e.printStackTrace();
         }
         return jsonString;
     }
}
