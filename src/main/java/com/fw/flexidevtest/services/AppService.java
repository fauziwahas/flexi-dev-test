package com.fw.flexidevtest.services;

import com.fw.flexidevtest.constants.MessageConstants;
import com.fw.flexidevtest.web.responses.AnswerResponse;
import java.text.DecimalFormat;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class AppService {

  /**
   *
   */
  public AnswerResponse calculateAverageNumberOfVictims(long ageAtDeath1, long yearOfDeath1, long ageAtDeath2, long yearOfDeath2) {
    /* validation */
    long yearOfBirth1 = yearOfDeath1 - ageAtDeath1;
    long yearOfBirth2 = yearOfDeath2 - ageAtDeath2;
    if(ageAtDeath1 < 0 || yearOfDeath1 < 0 || ageAtDeath2 < 0 || yearOfDeath2 < 0) {
      throw new IllegalArgumentException(MessageConstants.NEGATIVE_AGE_OF_DEATH + "<br>" + MessageConstants.NEGATIVE_YEAR_OF_DEATH);
    } else if(yearOfBirth1 < 0 || yearOfBirth2 < 0) {
      return new AnswerResponse("[]", "-1", "-1");
    }
    long start = yearOfBirth1 < yearOfBirth2 ? yearOfBirth1 : yearOfBirth2;
    long last = yearOfBirth1 < yearOfBirth2 ? yearOfBirth2 : yearOfBirth1;
    long[] years = sumOfFibonacciElements(last + 2);
    DecimalFormat decimalFormat = new DecimalFormat("#0.000");
    String sequence = formatSequence(years, start, last);
    String avgBetween = decimalFormat.format(calculateAverageBetween(years, start, last));
    String avgOfTwo = decimalFormat.format(((double)(years[(int)start]) + (double)(years[(int)last])) / (double)(2));
    return new AnswerResponse(sequence, avgBetween, avgOfTwo);
  }

  /**
   *
   */
  private long[] sumOfFibonacciElements(long lastIndex) {
    if(lastIndex < 0) {
      throw new IllegalArgumentException(MessageConstants.NEGATIVE_NUMBER_OF_ELEMENTS);
    }
    if(lastIndex == 2) {
      return new long[]{0, 1, 2};
    }
    long[] elements = new long[(int)(lastIndex) + 1];
    elements[0] = 0;
    elements[1] = 1;
    elements[2] = 1;
    long[] retval = new long[(int)(lastIndex) + 1];
    retval[0] = 0;
    retval[1] = 1;
    retval[2] = 2;
    long sum = 2;
    for(int i = 3; i < lastIndex + 1; i++) {
      elements[i] = elements[i - 1] + elements[i - 2];
      retval[i] = sum + elements[i];
      sum = retval[i];
    }
    return retval;
  }

  /**
   *
   */
  private double calculateAverageBetween(long[] years, long start, long last) {
    long sum = 0;
    for(long i = start; i < last + 1; i++) {
      sum += years[(int)(i)];
    }
    return (double)(sum) / (double)(last - start + 1);
  }

  /**
   *
   */
  private String formatSequence(long[] years, long start, long last) {
    String[] yearsStr = Arrays.toString(years).split(" ");
    yearsStr[(int)(start)] = "<b><red><u>" + yearsStr[(int)(start)] + "</u>";
    yearsStr[(int)(last)] = "<u>" + yearsStr[(int)(last)] + "</u></red></b>";
    return "From index " + start + " to index " + last + "<br>" + String.join(" ", yearsStr);
  }

}
