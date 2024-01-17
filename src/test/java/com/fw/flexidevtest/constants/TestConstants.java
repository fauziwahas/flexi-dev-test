package com.fw.flexidevtest.constants;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TestConstants {

  public static final String SUBSTRING_JSON_PARSE_ERROR_MESSAGE = "JSON parse error";
  public static final String SUBSTRING_METHOD_ARGUMENT_NOT_VALID_MESSAGE = "Method argument not valid";

  public static String payloadInvalidJson() {
    return "{\"object\":{C}";
  }

  public static String payloadApiSubmit(Object ageAtDeath1, Object yearOfDeath1, Object ageAtDeath2, Object yearOfDeath2) {
    String kv1 = ageAtDeath1 == null ? "\"ageAtDeath1\":null" : "\"ageAtDeath1\":" + ageAtDeath1;
    String kv2 = yearOfDeath1 == null ? "\"yearOfDeath1\":null" : "\"yearOfDeath1\":" + yearOfDeath1;
    String kv3 = ageAtDeath2 == null ? "\"ageAtDeath2\":null" : "\"ageAtDeath2\":" + ageAtDeath2;
    String kv4 = yearOfDeath2 == null ? "\"yearOfDeath2\":null" : "\"yearOfDeath2\":" + yearOfDeath2;
    return "{" + kv1 + "," + kv2 + "," + kv3 + "," + kv4 + "}";
  }

  public static ResultActions doApiSubmit(MockMvc mockMvc, Object ageAtDeath1, Object yearOfDeath1, Object ageAtDeath2, Object yearOfDeath2) throws Exception {
    return mockMvc.perform(post("/api/submit")
      .contentType(MediaType.APPLICATION_JSON)
      .content(payloadApiSubmit(ageAtDeath1, yearOfDeath1, ageAtDeath2, yearOfDeath2))
    );
  }

}
