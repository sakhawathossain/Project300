var submissionURL = "ajaxsubmissions";
var submissionID;
var addSubmissionURL = "addsubmission";
var editSubmissionURL = "editsubmission"
var tempURL;
var action = 'edit';
var addSubmissionURL = "addsubmission";
var editSubmissionURL = "editsubmission";
var deleteSubmissionURL = "deletesubmission";

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
                "visible" : false
                
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
        $("#view_submission_download").attr("href", "downloadfile?filename="+submissionTable.cell(this, 3).data());
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

        tempURL = addSubmissionURL;
        $('#modal_label').html("Add Submission");
        $('#edit_submission_date').val("");
        $("#edit_submission_comment").val("");
        $('#edit_submission_date').val("06/01/2015 12:00:00");
        $('#modalSubmissionEdit').modal('show');
    });

    //show Submission Edit modal on button click
    $('#submissionTable tbody').on('click', 'td a.editbutton', function(e) {
        e.stopImmediatePropagation(); // stop the row selection when clicking on an icon

        tempURL = editSubmissionURL;
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

    
    $('#edit_submission').submit(function(event){
    	saveMedia();
    	event.preventDefault();
    });
    //Submit Submission Edit form
    
    /*
    $('#edit_submission').submit(function(event) {

    	//var formData = new FormData($('form')[0]);
    	//var formData = new FormData($('#edit_submission'));
    	 
//    	var request = new FormData();                   
//    	$.each(context.prototype.fileData, function(i, obj) { request.append(i, obj.value.files[0]); });    
//    	request.append('action', 'upload');
//    	request.append('id', response.obj.id);
    	
    	//var formData = new FormData($('#edit_submission')[0]);
    	alert('hitting URL: '+tempURL);

        $.ajax({
        	
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)

            url: tempURL, // the url where we want to POST            
            data: $('#edit_submission').serialize(), // our data object
            //data = formData,		// CAUTION: this line breaks the javascript
            //dataType: 'multipart/form-data', // what type of data do we expect back from the server
            //dataType: 'json'
            enctype: 'multipart/form-data',
            //processType=false,	// CAUTION: this line breaks the javascript
            //contentType: false,	// CAUTION:this line breaks the javascript
            //processData = false,	// CAUTION: this line breaks the javascript
            cache : false,
            success: function(data) {
                $('#modalSubmissionEdit').modal('hide');
                submissionTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });
    */

    //Submit Submission Delete form
    $('#buttonSubmissionDelete').on('click', function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: deleteSubmissionURL + "?id=" + submissionID, // the url where we want to POST
            encode: true,
            success: function(data) {
                $('#modalSubmissionDelete').modal('hide');
                submissionTable.ajax.reload();
            }
        }),
        event.preventDefault();
    });
    
    
});
    
    function saveMedia() {
    	var form = document.getElementById('edit_submission');
    	var formData = new FormData(form);
        //var formData = new FormData();
        formData.append('file', $('input[type=file]')[0].files[0]);
        //formData.append('commentTeacher', $('#commentTeacher')[0].files[0]);
        console.log("form data " + formData);
        $.ajax({
            url : 'addsubmission',
            data : formData,  
            //dataType: json,
            processData : false,
            contentType : false,
            type : 'POST',
            success : function(data) {
            	$('#modalSubmissionEdit').modal('hide');
                submissionTable.ajax.reload();
            },
            error : function(err) {
                alert("failed to upload data");
            }
        });
    }


