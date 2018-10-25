package com.mirc.app;

import org.springframework.web.bind.annotation.*;

@RestController
public class IndexControler {

//    @Autowired
//    IndexService indexService;
//
//    @Autowired
//    RestTemplate restTemplate;


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


}