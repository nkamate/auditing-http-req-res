package com.example.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestController {
    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name) {

        return String.format("Hello, %s", name);
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