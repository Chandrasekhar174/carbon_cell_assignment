package com.chandrasekahr.Spring_security_JWT.service;

import com.chandrasekahr.Spring_security_JWT.entity.ApiResponce;
import com.chandrasekahr.Spring_security_JWT.entity.Entry;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataEntryService {
    private String urlApi="https://api.publicapis.org/entries";

    private static final RestTemplate restTemplate=new RestTemplate();


    public List<Entry>filterdata(String categories,Integer limit) throws NoSuchFieldException {
//        String apiUrl = urlApi + "?Category=" + categories;
        String responce=restTemplate.getForObject(urlApi,String.class);
        Gson gson=new Gson();
        ApiResponce apiResponce=gson.fromJson(responce,ApiResponce.class);
        List<Entry>entries=apiResponce.getEntries().stream()
                .filter(entry->entry.getCategory().equalsIgnoreCase(categories)).limit(limit)
                .collect(Collectors.toList());
        return entries;
    }
}
