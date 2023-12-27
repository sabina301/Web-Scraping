package com.my.parsingWebsite.service;

import com.my.parsingWebsite.entity.LangEntity;
import com.my.parsingWebsite.repository.LangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LangService {

    @Autowired
    private LangRepository langRepository;

    public void save(LangEntity lang){
        langRepository.save(lang);
    }
}
