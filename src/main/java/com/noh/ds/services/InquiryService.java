package com.noh.ds.services;

import java.util.Map;

public interface InquiryService {
    public String inquiry();
    public String validateInquiryRequest(Map<String, String> headers, String request);
}
