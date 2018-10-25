package com.mirc.serverrouter.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "mirc-server-router")
public interface RouterInter {
    @GetMapping(value = "/aa")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
