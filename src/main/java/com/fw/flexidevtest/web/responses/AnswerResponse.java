package com.fw.flexidevtest.web.responses;

public class AnswerResponse {

  private String sequence;
  private String avgBetween;
  private String avgOfTwo;

  public AnswerResponse(String sequence, String avgBetween, String avgOfTwo) {
    this.sequence = sequence;
    this.avgBetween = avgBetween;
    this.avgOfTwo = avgOfTwo;
  }

  public String getSequence() {
    return sequence;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }

  public String getAvgBetween() {
    return avgBetween;
  }

  public void setAvgBetween(String avgBetween) {
    this.avgBetween = avgBetween;
  }

  public String getAvgOfTwo() {
    return avgOfTwo;
  }

  public void setAvgOfTwo(String avgOfTwo) {
    this.avgOfTwo = avgOfTwo;
  }

}
