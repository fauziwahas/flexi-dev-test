package com.fw.flexidevtest.web.requests;

import com.fw.flexidevtest.constants.MessageConstants;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SubmitRequest {

  @NotNull(message = MessageConstants.AGE_AT_DEATH_NOT_NULL)
  @Min(value = 0)
  private Long ageAtDeath1;

  @NotNull(message = MessageConstants.YEAR_OF_DEATH_NOT_NULL)
  @Min(value = 0)
  private Long yearOfDeath1;

  @NotNull(message = MessageConstants.AGE_AT_DEATH_NOT_NULL)
  @Min(value = 0)
  private Long ageAtDeath2;

  @NotNull(message = MessageConstants.YEAR_OF_DEATH_NOT_NULL)
  @Min(value = 0)
  private Long yearOfDeath2;

  public Long getAgeAtDeath1() {
    return ageAtDeath1;
  }

  public void setAgeAtDeath1(Long ageAtDeath1) {
    this.ageAtDeath1 = ageAtDeath1;
  }

  public Long getYearOfDeath1() {
    return yearOfDeath1;
  }

  public void setYearOfDeath1(Long yearOfDeath1) {
    this.yearOfDeath1 = yearOfDeath1;
  }

  public Long getAgeAtDeath2() {
    return ageAtDeath2;
  }

  public void setAgeAtDeath2(Long ageAtDeath2) {
    this.ageAtDeath2 = ageAtDeath2;
  }

  public Long getYearOfDeath2() {
    return yearOfDeath2;
  }

  public void setYearOfDeath2(Long yearOfDeath2) {
    this.yearOfDeath2 = yearOfDeath2;
  }

}
