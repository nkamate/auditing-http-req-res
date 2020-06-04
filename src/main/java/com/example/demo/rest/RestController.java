package com.example.demo.rest;

import com.example.demo.model.Registration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {
    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name) {

        return String.format("Hello, %s", name);
    }

    @PostMapping(value = "/hello1")
    @ResponseBody
    public String helloPost(@RequestBody String name) {
        return String.format("Hello, %s", name);
    }

    @PostMapping("/registration")
    public ResponseEntity<Registration> register(@RequestBody Registration registration) {
        return new ResponseEntity<Registration>(registration, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@GetMapping(value = "/history")
    @ResponseBody
    public Object getHistory(@RequestParam(name = "instanceId") String instanceId) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        HistoricActivityInstanceQuery historicActivityInstanceQuery = defaultProcessEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .processInstanceId(instanceId);
        List<HistoricActivityInstance> activityInstances = historicActivityInstanceQuery.list();

        return activityInstances;
    }*/
}