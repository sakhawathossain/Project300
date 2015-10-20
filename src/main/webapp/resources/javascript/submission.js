var submissionURL = "ajaxsubmissions";
var submissionID;
var action = 'edit';

$(document).ready(function() {
    var submissionTable;
    $('[data-toggle="tooltip"]').tooltip();
    submissionTable = $('#submissionTable').DataTable({
        "dom": 'lrtip',
        "processing": true,
        //"serverSide": true,
        "lengthChange": true,
        "autoWidth": false,
        "aoColumns": [{
                "mData": 0,
                //"visible": false
            }, {
                "sWidth": "20%",
                "mData": 1
            }, {
                //"sWidth": "20%",
                "mData": 2,
            },  {
                //"sWidth": "20%",
                "mData": 3,
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
            url: submissionURL,
            type: 'get',
            dataType: 'json'
        }
    });

    //$('.datepicker').datepicker();
    //total groups, total submission

    //show Info Modal
    $('#submissionTable tbody').on('click', 'tr', function() {
        $('#view_submission_id').html(submissionTable.cell(this, 0).data());
        $('#view_submission_date').html(submissionTable.cell(this, 1).data());
        $("#view_submission_comment").html(submissionTable.cell(this, 2).data());
        $("#view_submission_download").attr("href", "#");
        $('#modalSubmissionInfo').modal('show');
     });
    
    //go to project-groups.html on row click
    /*
    $('#submissionTable tbody').on('click', 'tr', function() {
        window.location.href = "project-groups.html";
    });
    */

    //show Submission Add modal on button click
    $('#button_add_submission').on('click', function(event) {
        action = 'add';
        $('#modal_label').html("Add Submission");
        $('#edit_submission_date').val("");
        $("#edit_submission_comment").val("");
        $('#modalSubmissionEdit').modal('show');
    });

    //show Submission Edit modal on button click
    $('#submissionTable tbody').on('click', 'td a.editbutton', function(e) {
        e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
        action = 'edit';
        var rowIndex = submissionTable.cell($(this).parent()).index().row;
        submissionID = submissionTable.cell(rowIndex, 0).data();
        $('#modal_label').html("Edit Submission");
        $('#edit_submission_date').val(submissionTable.cell(rowIndex, 1).data());
        $("#edit_submission_comment").val(submissionTable.cell(rowIndex, 2).data());
        $('#modalSubmissionEdit').modal('show');
    });


    //show Submission Delete Modal on button click
    $('#submissionTable tbody').on('click', 'td a.removebutton', function(e) {
        e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
        var rowIndex = submissionTable.cell($(this).parent()).index().row;
        submissionID = submissionTable.cell(rowIndex, 0).data();
        $('#modalSubmissionDelete').modal('show');
    });

    //Submit Submission Edit form
    $('#edit_submission').submit(function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: submissionURL + '?action=' + action + '&', // the url where we want to POST
            data: $('#edit_submission').serialize(), // our data object
            //dataType: 'json', // what type of data do we expect back from the server
            encode: true,
            success: function(data) {
                $('#modalSubmissionEdit').modal('hide');
                submissionTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });

    //Submit Submission Delete form
    $('#buttonSubmissionDelete').on('click', function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: submissionURL + "?action=delete&id=" + submissionID, // the url where we want to POST
            encode: true,
            success: function(data) {
                $('#modalSubmissionDelete').modal('hide');
                submissionTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });
});