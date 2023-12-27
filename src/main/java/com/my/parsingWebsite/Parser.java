package com.my.parsingWebsite;

import com.my.parsingWebsite.entity.LangEntity;
import com.my.parsingWebsite.entity.RepEntity;
import com.my.parsingWebsite.service.LangService;
import com.my.parsingWebsite.service.RepService;
import jakarta.transaction.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class Parser {

    @Autowired
    private RepService repService;

    @Autowired
    private LangService langService;

    @Scheduled(fixedDelay = 10000)
    public void parse() {

        String username = "sabina301";

        String url = "https://github.com/" + username + "?tab=repositories";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Elements divRepositories = doc.getElementsByClass("wb-break-all");
            for (Element divRepository: divRepositories) {
                Element linkRepository = divRepository.selectFirst("a");
                String textRepository = linkRepository.text();
                if (!repService.isExist(textRepository)){
                    RepEntity rep = new RepEntity();
                    rep.setRepName(textRepository);
                    repService.saveRep(rep);

                    String urlRep = "https://github.com/" + username + "/" + textRepository;
                    Document docRep = Jsoup.connect(urlRep)
                            .userAgent("Mozilla")
                            .timeout(5000)
                            .referrer("https://google.com")
                            .get();
                    Elements allInfoRep = docRep.select("a.d-inline-flex.flex-items-center.flex-nowrap.Link--secondary.no-underline.text-small.mr-3 > span");

                    List<List<Object>> infoList = new ArrayList<>();
                    List<Object> innerList = new ArrayList<>();
                    for (int i = 0; i < allInfoRep.size(); i++) {
                        Element langInfo = allInfoRep.get(i);
                        if (i % 2 == 0) {
                            innerList.add(langInfo.text());
                        } else {
                            innerList.add(langInfo.text());
                            infoList.add(innerList);
                            innerList = new ArrayList<>();
                        }
                    }

                    for (List<Object> info: infoList){
                        String percentStr = (String) info.get(1);
                        percentStr = percentStr.replace("%","");
                        Double percent =Double.parseDouble(percentStr);

                        LangEntity lang = new LangEntity();

                        lang.setLangName((String) info.get(0));
                        lang.setPercent(percent);
                        RepEntity repEntity = repService.findOne(textRepository);
                        lang.setRep(repEntity);

                        langService.save(lang);
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}