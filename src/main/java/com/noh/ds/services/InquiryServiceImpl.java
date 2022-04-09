package com.noh.ds.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.noh.ds.security.SecurityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    SecurityManager securityManager;

    @Override
    public String inquiry() {
        return null;
    }

    /**
     * return account detail
     * @param headers
     * @param request
     * @return
     */
    @Override
    public String validateInquiryRequest(Map<String, String> headers, String request) {
        HttpHeaders reqHeaders = null;
        Gson gson = new Gson();
        JsonObject requestBody = new JsonObject();

        requestBody.addProperty("frommember", "0302000410000000");
        requestBody.addProperty("fromuser", "nohder");
        requestBody.addProperty("fromaccount", "bouder");
        requestBody.addProperty("tomember", "IB");
        requestBody.addProperty("toaccount", "0100000000000");
        requestBody.addProperty("totype", "type");
        requestBody.addProperty("reference", "1234567890");
        requestBody.addProperty("time", "2020-10-0905:34:11");
        String requestString = gson.toJson(requestBody);
        System.out.println(requestString);

        reqHeaders = new HttpHeaders();
        reqHeaders.set("Content-Type", "application/json");
        reqHeaders.set("x-source-request-signature", this.securityManager.generateSignature(requestString));
        String reqUrl = "http://127.0.0.1:8899/in/";
        this.log.debug("Request URL : " + reqUrl);
        this.log.debug("Request Body : " + requestString);
        HttpEntity<String> requestEntity = new HttpEntity(requestString, reqHeaders);


        return null;
    }

    public static void main(String arg[]) {
        Gson gson = new Gson();
        JsonObject requestBody = new JsonObject();

        requestBody.addProperty("frommember", "0302000410000000");
        requestBody.addProperty("fromuser", "nohder");
        requestBody.addProperty("fromaccount", "bouder");
        requestBody.addProperty("tomember", "IB");
        requestBody.addProperty("toaccount", "0100000000000");
        requestBody.addProperty("totype", "type");
        requestBody.addProperty("reference", "1234567890");
        requestBody.addProperty("time", "2020-10-0905:34:11");
        System.out.println(requestBody.toString());
        String requestString = gson.toJson(requestBody);

        System.out.println(requestString);
    }
}
