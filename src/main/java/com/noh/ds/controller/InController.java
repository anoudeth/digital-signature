package com.noh.ds.controller;

import com.noh.ds.services.InquiryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/in")
public class InController {

    @Autowired
    InquiryServiceImpl inquiryService;

    @PostMapping("/inquiry")
    public ResponseEntity<?> inquiry(@RequestHeader Map<String, String> headers, @RequestBody String request) {

        this.inquiryService.inquiry();
        return ResponseEntity.ok("resp inquiry");
    }

    @PostMapping("/test")
    public ResponseEntity<?> inquiry() {
        return ResponseEntity.ok("test der");
    }
}
