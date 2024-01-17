package com.fw.flexidevtest.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fw.flexidevtest.web.responses.AnswerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.fw.flexidevtest.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class TestApiSubmit {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  /**
   *
   */
  @Test
  void givenInvalidJson_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = mockMvc.perform(post("/api/submit")
      .contentType(MediaType.APPLICATION_JSON)
      .content(payloadInvalidJson())
    ).andExpect(status().isBadRequest())
    .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_JSON_PARSE_ERROR_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenNegativeAgeAtDeath1_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, -4l, 12l, 13l, 17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_METHOD_ARGUMENT_NOT_VALID_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenNegativeYearOfDeath1_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, -12l, 13l, 17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_METHOD_ARGUMENT_NOT_VALID_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenNegativeAgeAtDeath2_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, 12l, -13l, 17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_METHOD_ARGUMENT_NOT_VALID_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenNegativeYearOfDeath2_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, 12l, 13l, -17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_METHOD_ARGUMENT_NOT_VALID_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenInvalidAgeAtDeath1_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, "10asd", 12l, 13l, -17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_JSON_PARSE_ERROR_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenInvalidYearOfDeath1_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, "12l", 13l, -17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_JSON_PARSE_ERROR_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenInvalidAgeAtDeath2_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, 12l, "13l", -17l)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_JSON_PARSE_ERROR_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenInvalidYearOfDeath2_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, 12l, 13l, "-17l")
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_JSON_PARSE_ERROR_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenNegativeYearOfBirth1_whenSubmitting_assert200AndCorrectResponse() throws Exception {
    String response = doApiSubmit(mockMvc, 14l, 12l, 13l, 17l)
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    AnswerResponse answerResponse = objectMapper.readValue(response, AnswerResponse.class);
    assertEquals("[]", answerResponse.getSequence());
    assertEquals("-1", answerResponse.getAvgBetween());
    assertEquals("-1", answerResponse.getAvgOfTwo());
  }

  /**
   *
   */
  @Test
  void givenNegativeYearOfBirth2_whenSubmitting_assert200AndCorrectResponse() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, 12l, 19l, 17l)
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    AnswerResponse answerResponse = objectMapper.readValue(response, AnswerResponse.class);
    assertEquals("[]", answerResponse.getSequence());
    assertEquals("-1", answerResponse.getAvgBetween());
    assertEquals("-1", answerResponse.getAvgOfTwo());
  }

  /**
   *
   */
  @Test
  void givenAllNull_whenSubmitting_assert400AndCorrectMessage() throws Exception {
    String response = doApiSubmit(mockMvc, null, null, null, null)
      .andExpect(status().isBadRequest())
      .andReturn().getResponse().getContentAsString();
    assertTrue(response.contains(SUBSTRING_METHOD_ARGUMENT_NOT_VALID_MESSAGE));
  }

  /**
   *
   */
  @Test
  void givenValidPayload_whenSubmitting_assert200AndCorrectResponse() throws Exception {
    String response = doApiSubmit(mockMvc, 10l, 12l, 13l, 17l)
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    AnswerResponse answerResponse = objectMapper.readValue(response, AnswerResponse.class);
    assertTrue(answerResponse.getSequence().contains("<red>"));
    assertTrue(answerResponse.getSequence().contains("</red>"));
    assertTrue(answerResponse.getAvgBetween().equals("4.333"));
    assertTrue(answerResponse.getAvgOfTwo().equals("4.500"));
  }

}
