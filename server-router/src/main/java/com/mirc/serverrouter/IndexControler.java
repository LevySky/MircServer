package com.mirc.serverrouter;

import com.mirc.serverrouter.inter.RouterInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexControler {

//    @Autowired
//    IndexService indexService;
//
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    RouterInter routerInter;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        System.out.println(name);
        return routerInter.sayHiFromClientOne( name );
    }

    @GetMapping(value = "/bgbc")
    public String hi() {
        return "aaaaaaaaaa";//indexService.hiService( api );
    }


    @GetMapping("/aa")
    String sayHiFromClientOne(@RequestParam(value = "name") String name){
        return "aa"+name;
    }

    @RequestMapping("/securedPage")
    String securedPage(){
        return "securedPage";
    }

    @RequestMapping(value="/router",method= RequestMethod.GET)
    String router(String url){
        return url;
    }

/*    @GetMapping(value="/login")
    public String getToken(@RequestParam String code){
        System.out.println("receive code {}"+code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("code",code);
        params.add("client_id","html");
        params.add("client_secret","html");
        params.add("redirect_uri","http://localhost:9066/getToken");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9066/oauth/token", requestEntity, String.class);
        String token = response.getBody();
        System.out.println("token => {}"+token);
        return token;
    }*/

}