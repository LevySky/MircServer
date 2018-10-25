//package com.mirc.serverrouter;
//
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class IndexService {
//
////    @Autowired
////    RestTemplate restTemplate;
//
//    @HystrixCommand(fallbackMethod = "hiError")
//    public String hiService(String api) {
//
//
//        return restTemplate.getForObject("http://bgbc/"+api,String.class);
//    }
//
//    @HystrixCommand(fallbackMethod = "bgbcError")
//    public String bgbcService(String api) {
//        return restTemplate.getForObject("http://bgbc/"+api,String.class);
//    }
//
//    public String bgbcError(String name) {
//        return "hi,"+name+",sorry,error!";
//    }
//
//
//}
