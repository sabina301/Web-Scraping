package com.my.parsingWebsite.controller;

import com.my.parsingWebsite.entity.RepEntity;
import com.my.parsingWebsite.service.RepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepController {

    @Autowired
    private RepService repService;

    @GetMapping("/rep")
    public List<RepEntity> getAllUserRep(){
        return repService.getAllUserRep();
    }
}
