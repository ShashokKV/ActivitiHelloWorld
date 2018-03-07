package com.activiti.helloworld;

import org.activiti.engine.task.Task;

public class TaskDto {

    private String assignee;
    private String id;

    public TaskDto(Task task) {
        this.assignee = task.getAssignee();
        this.id = task.getId();
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
