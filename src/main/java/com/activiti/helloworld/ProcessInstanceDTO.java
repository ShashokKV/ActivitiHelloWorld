package com.activiti.helloworld;

import org.activiti.engine.runtime.ProcessInstance;

public class ProcessInstanceDTO {

    private String id;
    private String name;

    public ProcessInstanceDTO(ProcessInstance processInstance) {
        this.setId(processInstance.getProcessInstanceId());
        this.setName(processInstance.getName());
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
