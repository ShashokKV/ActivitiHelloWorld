<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<%--<p>Processes count ${processCount}</p>--%>
<button type="button" onclick="" id="startProcessButton">Start Process</button>
<table id="active-process-table">
    <tbody id="active-process-tbody">
    </tbody>
</table>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $.ajax({
            type: "GET",
            url: "/api/getProcessList",
            success: function (data) {
                  drawProcessTable(data);
            }
        })
    });

    $("#startProcessButton").click(function(){
        $.when($.get("/api/startProcess")).done(getProcessesRequest());
    });

    function drawProcessTable(processDTOArray) {
        processDTOArray.forEach(function (processDTO) {
            if (processDTO != null) {
                $("#active-process-tbody").append('<tr>' +
                    '<td>Process ID:' + processDTO.id + '</td>' +
                    '/<tr>'+
                    '<tr>' +
                        '<td>'+
                            '<table id="active-tasks-table-'+processDTO.id+'">'+
                                '<tbody></tbody>'+
                            '</table>'+
                        '</td>'+
                    '</tr>'
                )
            }
            drawTasksTable(processDTO.id);
        });
    }

    function drawTasksTable(processId) {
        $.get("/api/getTasksList", {processId: processId})
            .done(function(tasksArray) {
                var tableId = "active-tasks-table-"+processId;
                $("#"+tableId).find("tbody").empty();
                tasksArray.forEach(function (taskDTO) {
                    $("#"+tableId).find("tbody:last-child").append('<tr>' +
                        '<td>Task ID:'+taskDTO.id+'</td>'+
                        '<td><button type="button" onclick="completeTask('+taskDTO.id+')" id="completeTaskButton">Complete Task</button></td>'+
                        '</tr>'
                    )
                })
            })
    }

    function cleanTableData() {
        $("#active-process-tbody").empty();
    }

    function completeTask(taskId) {
          var request = $.get("/api/completeTask", {taskId: taskId});

            $.when(request).done(function(){
                    alert("Task "+taskId+" complete");
                    getProcessesRequest()
            });
    }

    function getProcessesRequest(){
        $.ajax({
            type: "GET",
            url: "/api/getProcessList",
            success: function (data) {
                cleanTableData();
                drawProcessTable(data);
            },
            error: function (error) {
                alert("Error: " + error);
            }
        });
    }
</script>
</body>

</html>