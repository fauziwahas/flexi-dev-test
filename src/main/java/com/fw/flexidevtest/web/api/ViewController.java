package com.fw.flexidevtest.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  @GetMapping("/")
  public String index() throws Exception {
    return "index";
  }

}
