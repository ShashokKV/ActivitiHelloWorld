package com.activiti.helloworld;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

    private RuntimeService runtimeService;

    public HelloWorldController() {
    }

//    @Autowired
//    public HelloWorldController(RuntimeService runtimeService) {
//        this.runtimeService = runtimeService;
//    }

    @GetMapping(value = "/sayHello")
        public String sayHello(Model model) {
        System.out.println("started helloWorld Controller");
//        runtimeService.startProcessInstanceByKey("HW");
//
//        model.addAttribute("processCount", runtimeService.createProcessInstanceQuery().count());
//        model.addAttribute("processList",  runtimeService.createProcessInstanceQuery().list());
        return "home";
    }


}
