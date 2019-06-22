package com.cd.rest.controller;

import com.cd.rest.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private Producer producer;

    @GetMapping
    public ResponseEntity<Boolean> heartbeat() {
        this.producer.sendMessage("test", "key1", "test heartbeat");
        return ResponseEntity.ok(true);
    }
}
