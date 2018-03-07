package com.activiti.helloworld;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ApiRestController {

    private final RuntimeService runtimeService;
    private final ProcessEngine processEngine;

    @Autowired
    public ApiRestController(RuntimeService runtimeService, ProcessEngine processEngine) {
        this.runtimeService = runtimeService;
        this.processEngine = processEngine;
    }

    @GetMapping("/api/startProcess")
    public boolean startProcess(){
        System.out.println("Hello From Rest");
        runtimeService.startProcessInstanceByKey("HW");
        return true;
    }

    @GetMapping("/api/getProcessList")
    public List<ProcessInstanceDTO> getProcessList() {
        System.out.println("getProcess List");
        return getProcessDTOList();
    }

    @GetMapping("/api/getTasksList")
    public List<TaskDto> getTasksList(@RequestParam String processId) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().active().processInstanceId(processId).list();
        return taskList.stream()
                .map(TaskDto::new).collect(Collectors.toList());
    }

    @GetMapping("/api/completeTask")
    public void completeTask(@RequestParam String taskId) {
        TaskService taskService = processEngine.getTaskService();
        FormService formService = processEngine.getFormService();
        FormData formData = formService.getTaskFormData(taskId);
        Map<String, Object> variables = new HashMap<>();
        for (FormProperty formProperty : formData.getFormProperties()) {
            System.out.println("form_id:"+formProperty.getId()+", form_name:"+formProperty.getName());
            variables.put(formProperty.getId(), "someValue");
        }
        taskService.complete(taskId, variables);
    }

    private List<ProcessInstanceDTO> getProcessDTOList() {
        return runtimeService.createProcessInstanceQuery().list().stream()
                .map(ProcessInstanceDTO::new).collect(Collectors.toList());
    }
}
