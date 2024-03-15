package com.opencampus.automation.bases;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;

public class BaseRestful {
    private final String hostName;

    public BaseRestful(String hostName) {
        this.hostName = hostName;
    }

    protected Response post(String endPoint, Object payLoad) {
        return getRequestSpecification(null, endPoint, payLoad, null, true).request(POST);
    }

    protected Response post(String endPoint, Object payLoad, Cookies cookies) {
        return getRequestSpecification(null, endPoint, payLoad, cookies, true).request(POST);
    }

    protected Response get(String endPoint, boolean allowRedirect) {
        return getRequestSpecification(null, endPoint, null, null, allowRedirect).request(GET);
    }

    protected Response get(String endPoint, Cookies cookies) {
        return getRequestSpecification(null, endPoint, null, cookies, true).request(GET);
    }

    private RequestSpecification getRequestSpecification(Headers headers, String endPoint, Object payLoad, Cookies cookies, boolean allowRedirect) {
        List<Header> defaultHeaders = new ArrayList<>();
        defaultHeaders.add(new Header("Accept", "*/*"));
        defaultHeaders.add(new Header("Content-Type", "application/json"));
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(this.hostName).setBasePath(endPoint);
        if (headers != null) {
            defaultHeaders.addAll(headers.asList());
        }
        Map<String, String> specHeaders = new HashMap<>();
        for (Header h : defaultHeaders) {
            specHeaders.putIfAbsent(h.getName(), h.getValue());
        }
        specBuilder.addHeaders(specHeaders);
        if (payLoad != null)
            specBuilder.setBody(payLoad);
        if (cookies != null) {
            for (Cookie cookie : cookies)
                specBuilder.addCookie(cookie);
        }
        if (endPoint.contains("?")) {
            String[] paramString = endPoint.split("\\?")[1].split("&");
            Map<String, Object> paramMap = Arrays.stream(paramString).map(p -> p.split("=")).collect(Collectors.toMap(p -> p[0].trim(), p -> p[1].trim()));
            specBuilder.addQueryParams(paramMap);
        }
        return given().config(RestAssured.config().redirect(redirectConfig().followRedirects(allowRedirect))).spec(specBuilder.build());
    }
}
