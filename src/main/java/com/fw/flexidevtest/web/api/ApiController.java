package com.fw.flexidevtest.web.api;

import com.fw.flexidevtest.services.AppService;
import com.fw.flexidevtest.web.requests.SubmitRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  private AppService appService;

  public ApiController(AppService appService) {
    this.appService = appService;
  }

  @PostMapping("/api/submit")
  public ResponseEntity<?> submit(@RequestBody @Validated SubmitRequest request) {
    return ResponseEntity.ok().body(appService.calculateAverageNumberOfVictims(request.getAgeAtDeath1(), request.getYearOfDeath1(), request.getAgeAtDeath2(), request.getYearOfDeath2()));
  }

}
