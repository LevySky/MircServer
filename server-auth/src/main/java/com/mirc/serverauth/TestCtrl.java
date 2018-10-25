package com.mirc.serverauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
public class TestCtrl {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    AuthorizationServerTokenServices serverTokenServices;


    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }



    @GetMapping("/aa")
    public String user(){
        serverTokenServices.toString();
        tokenStore.toString();
        tokenStore.readAccessToken("").getValue();
        return "aaaaaaaaaaaaaaaa";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }


    @RequestMapping("/getToken")
    public String getToken(@RequestParam String code){
        System.out.println("receive code {}"+code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("code",code);
        params.add("client_id","html");
        params.add("client_secret","html");
        params.add("redirect_uri","http://localhost:9066/at/getToken");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9066/at/oauth/token", requestEntity, String.class);
        String token = response.getBody();
        System.out.println("token => {}"+token);
        return token;
    }
}
