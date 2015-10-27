$(document).ready(function() {
    //var taskTable;
    //$('[data-toggle="tooltip"]').tooltip();
    
//    $('#courseTable tbody').on('click', 'tr', function() {
//        id = projectTable.cell(this, 0).data();
//        
//        $('#view_id').html(id);
//        $('#view_title').html(projectTable.cell(this, 1).data());
//        $('#view_submission_date').html(projectTable.cell(this, 2).data());
//        $('#view_members').html(projectTable.cell(this, 3).data());
//        $('#modalInfo').modal('show');
//    	alert('captured the click event');
//    	window.location.replace("tasks");
//        });
	alert('click!');
	$('#course_table').on('click', 'th', function(event) {
		alert('click click!');
    });
    	
});