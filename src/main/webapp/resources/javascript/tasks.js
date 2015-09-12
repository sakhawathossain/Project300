var taskURL = "ajaxtasks";
var addTaskURL="addtask";
var editTaskURL="edittask";
var deleteTaskURL = "deletetask";
var taskID;
var delID;

$(document).ready(function() {
    var taskTable;
    $('[data-toggle="tooltip"]').tooltip();
    taskTable = $('#taskTable').DataTable({
        "dom": 'lrtip',
        "processing": true,
        //"serverSide": true,
        "lengthChange": true,
        "autoWidth": false,
        "aoColumns": [{
                "mData": 0,
                "visible": false
            }, {
                "sWidth": "20%",
                "mData": 1
            }, {
                //"sWidth": "20%",
                "mData": 2,
            }, {
                "mData": 3,
                "visible": false
            },
            {
                "mData": 4,
                //"visible": false
            },
            {
                "mData": 5,
                "visible": false
            },
            {
                "mData": 6,
                "visible": false
            },
            {
                "mData": 7,
                "visible": false
            },
            {
                "mData": null,
                "bSortable": false,
                "sWidth": "10%",
                "mRender": function(data, type, full) {
                    return '<a class="btn btn-info btn-sm editbutton"><i class="glyphicon glyphicon-edit "></i></a>'
                            + '<a class="btn btn-danger btn-sm removebutton"><i class="glyphicon glyphicon-remove "></i></a>';
                }
            }],
        ajax: {
            url: taskURL,
            type: 'get',
            dataType: 'json'
        }
    });

    //$('.datepicker').datepicker();
    //total groups, total submission

    //show Info Modal
    /*$('#taskTable tbody').on('click', 'tr', function() {
     taskID = taskTable.cell(this, 0).data();
     $('#view_task_id').html(taskID);
     $('#view_task_title').html(taskTable.cell(this, 1).data());
     $('#view_task_type').html(taskTable.cell(this, 2).data());
     $('#view_task_description').html(taskTable.cell(this, 3).data());
     $('#modalTaskInfo').modal('show');
     });*/
    
    //go to project-groups.html on row click
    $('#taskTable tbody').on('click', 'tr', function() {
        window.location.href = "project-groups.html";
    });

    //show Task Add modal on button click
    $('#button_add_task').on('click', function(event) {
        $('#modal_label').html("Add task");
        $('#edit_task_title').val("Borrow some idea");
        $('#edit_task_description').val("Lorem Ipsum");
        $('#edit_task_deadline').val("06/01/2015 12:00:00");
        $('#edit_task_group_number').val("10");
        $('#edit_task_total_submission').val("11");
        $('edit_task_sopen').val("true");
        $("#edit_task_type").val("1");
        $('#edit_task_description').val("");
        $('#modalTaskEdit').modal('show');
    });

    //show Task Edit modal on button click
    $('#taskTable tbody').on('click', 'td a.editbutton', function(e) {
        e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
        var rowIndex = taskTable.cell($(this).parent()).index().row;
        taskID = taskTable.cell(rowIndex, 0).data();
        $('#modal_label').html("Edit task");
        $('#edit_task_title').val(taskTable.cell(rowIndex, 1).data());
        $("#edit_task_type").val(taskTable.cell(rowIndex, 2).data());
        $('#edit_task_description').val(taskTable.cell(rowIndex, 3).data());
        $('#edit_task_deadline').val(taskTable.cell(rowIndex, 4).data());
        
        if (taskTable.cell(rowIndex, 5).data() == "true")
            $('#edit_task_sopen').prop('checked', true);
        else
            $('#edit_task_sopen').prop('checked', false);
        $('#modalTaskEdit').modal('show');
        $('#edit_task_group_number').val(taskTable.cell(rowIndex, 6).data());
        $('#edit_task_total_submission').val(taskTable.cell(rowIndex, 7).data());


        
    });


    //show Task Delete Modal on button click
    $('#taskTable tbody').on('click', 'td a.removebutton', function(e) {
        e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
        var rowIndex = taskTable.cell($(this).parent()).index().row;
        taskID = taskTable.cell(rowIndex, 0).data();
        $('#modalTaskDelete').modal('show');
    });

    //Submit Task Add form
    $('#edit_task').submit(function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: addTaskURL, // the url where we want to POST
            data: $('#edit_task').serialize(), // our data object
            dataType: 'json', // what type of data do we expect back from the server
            encode: true,
            success: function(data) {
                $('#modalTaskEdit').modal('hide');
                taskTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });

    //Submit Task Edit form
    $('#edit_task').submit(function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: editTaskURL + '?taskId=' + taskID, // the url where we want to POST
            data: $('#edit_task').serialize(), // our data object
            dataType: 'json', // what type of data do we expect back from the server
            encode: true,
            success: function(data) {
                $('#modalTaskEdit').modal('hide');
                taskTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });

    //Submit Task Delete form
    $('#delete_task').submit(function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: deleteTaskURL + '?taskId=' + taskID,//the url where we want to POST
            data: $('#delete_task').serialize(),
            dataType: 'json',// 
            encode: true,
            success: function(data) {
                $('#modalTaskDelete').modal('hide');
                taskTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });
});