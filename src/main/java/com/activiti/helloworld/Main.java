package com.activiti.helloworld;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

public class Main {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngineConfiguration.
                createStandaloneInMemProcessEngineConfiguration().
                buildProcessEngine();

        processEngine.getRepositoryService().
                createDeployment().
                addClasspathResource("Hello_World.bpmn20.xml").
                deploy();

        ProcessInstance processInstance = processEngine.
                getRuntimeService().
                startProcessInstanceByKey("HW");

    }
}
