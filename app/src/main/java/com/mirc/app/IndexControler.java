package com.mirc.app;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class IndexControler {

    //    @Autowired
//    IndexService indexService;
//
    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/bgbc")
    public String hi() {
        return "aaaaaaaaaa";//indexService.hiService( api );
    }


    @GetMapping("/aa")
    String sayHiFromClientOne(@RequestParam(value = "name") String name) {
        return "aa" + name;
    }

    @RequestMapping("/securedPage")
    String securedPage() {
        return "securedPage";
    }

    @RequestMapping(value = "/router", method = RequestMethod.GET)
    String router(String url) {
        return url;
    }


    @Value("${foo}")
    String foo;
    @RequestMapping(value = "/test")
    public String foo() {
        return foo;
    }

    @RequestMapping(value = "/refresh")
    public String refresh() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        restTemplate.postForEntity("http://mirc-server-app/app/actuator/bus-refresh",requestEntity, String.class);
        return foo;
    }

}