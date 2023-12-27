package com.my.parsingWebsite.service;

import com.my.parsingWebsite.entity.RepEntity;
import com.my.parsingWebsite.repository.RepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepService {

    @Autowired
    private RepRepository repRepository;

    public List<RepEntity> getAllUserRep(){
        return repRepository.findAll();
    }

    public boolean isExist(String repName){
        List<RepEntity> repositories = repRepository.findAll();
        for (RepEntity rep : repositories){
            if (rep.getRepName().equals(repName)){
                return true;
            }
        }
        return false;
    }

    public void saveRep(RepEntity rep){
        repRepository.save(rep);
    }

    public RepEntity findOne(String repName){
        try {
            return repRepository.findByRepName(repName).orElseThrow(()->new Exception());
        } catch (Exception e){
            return null;
        }
    }
}
