var projectURL = "ajaxprojects";
var groupURL = "ajaxgroups";
var addProjectURL = "addproject";
var updateProjectURL = "updateproject";
var deleteProjectURL = "deleteproject";
var addGroupURL = "addgroup";
var updateGroupURL = "updategroup";
var deleteGroupURL = "deletegroup";
var tempProjectURL
var projectID;
var groupID;
var memberCounter;
var maxAllowedMembers = 6

$(document).ready(function() {
    var projectTable;
    var groupTable;
    $('[data-toggle="tooltip"]').tooltip();
    projectTable = $('#projectTable').DataTable({
        "dom": 'lrtip',
        "processing": true,
        //"serverSide": true,
        "lengthChange": true,
        "autoWidth": false,
        "fnInitComplete": function(oSettings, json) {
            $('#projectTable tbody tr:eq(0)').click();
        },
        "aoColumns": [{
                "mData": 0,
                "visible": false
            }, {
                "sWidth": "40%",
                "mData": 1
            }, {
                "sWidth": "40%",
                "mData": 2
                //"visible": false
            }, 
            {
                "mData": null,
                "bSortable": false,
                "mRender": function(data, type, full) {
                    return '<a class="btn btn-info btn-sm editbutton"><i class="glyphicon glyphicon-edit "></i></a>'
                            + '<a class="btn btn-danger btn-sm removebutton"><i class="glyphicon glyphicon-remove "></i></a>';
                }
            }],
        ajax: {
        	
            url: projectURL+"?task_id="+getUrlVars()["task_id"],
            dataType: 'json'
        }
       
    });
    
    

    groupTable = $('#groupTable').DataTable({
        "dom": 'lrtip',
        "processing": true,
        //"serverSide": true,
        "lengthChange": true,
        "autoWidth": false,
        "aoColumns": [{
                "mData": 0,
            }, {
                //"sWidth": "20%",
                "mData": 1,
                "bSortable": false,
                "mRender": function(data, type, full) {
                    var render = "";
                    for (var i = 0; i < data.length; i++) {
                        render = render + '<span class="group-member"'
                                + 'data-toggle="tooltip" data-placement="bottom" title="' + data[i] + '"'
                                + 'style="background-color:' + "#00BCD4" + '">'
                                + data[i].substring(8) + '</span>';
                    }
                    return render;
                }
            }, {
                "mData": 2
            }, {
                "mData": 3,
                "visible": false
            }, {
                "mData": null,
                "bSortable": false,
                "mRender": function(data, type, full) {
                    //return '<a class="btn btn-info btn-sm" href=#/' + full[0] + '>' + '<i class="glyphicon glyphicon-edit "></i>' + '</a>';
                    //return '<a class="btn btn-info btn-sm"><i class="glyphicon glyphicon-edit "></i>' + '</a>';
                    return '<a class="btn btn-info btn-sm editbutton"><i class="glyphicon glyphicon-edit "></i></a>'
                            + '<a class="btn btn-danger btn-sm removebutton"><i class="glyphicon glyphicon-remove "></i></a>';
                }
            }],
        //ajax data loads when projectTable clicks row, first row is clicked automatically on init
        //ajax: {
        //    url: groupURL,
        //    dataType: 'json'
        //}
    });

    //show Info Modal
    /*
     $('#projectTable tbody').on('click', 'tr', function() {
     id = projectTable.cell(this, 0).data();
     
     $('#view_id').html(id);
     $('#view_title').html(projectTable.cell(this, 1).data());
     $('#view_submission_date').html(projectTable.cell(this, 2).data());
     $('#view_members').html(projectTable.cell(this, 3).data());
     $('#modalInfo').modal('show');
     });*/

    //Load Group data on Project row click
    $('#projectTable tbody').on('click', 'tr', function() {
        projectID = projectTable.cell(this, 0).data();
        projectTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
        groupURL = "testservlet/groups" + "?projectID=" + projectID;
        groupTable.ajax.url(groupURL).load();
    });

    
  //show Project Add modal on button click
    $('#button_add_project').on('click', function(event) {
    	$('#modal_project_label').html("Add Project");
        tempProjectURL = addProjectURL;
        $('#edit_project_title').val("");
        $('#edit_project_submission_date').val("");
        $('#modalProjectEdit').modal('show');
    });
    
    //show Project Edit modal on button click
    $('#projectTable tbody').on('click', 'td a.editbutton', function(e) {
    	$('#modal_project_label').html("Edit Project");
        //e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
    	tempProjectURL = updateProjectURL;
        var rowIndex = projectTable.cell($(this).parent()).index().row;
        projectID = projectTable.cell(rowIndex, 0).data();
        //alert('clicked button for id: ' + id);
        $('#edit_project_title').val(projectTable.cell(rowIndex, 1).data());
        $('#edit_project_desc').val(projectTable.cell(rowIndex, 2).data());
        $('#modalProjectEdit').modal('show');
    });

    //show Group Edit modal on button click
    $('#groupTable tbody').on('click', 'td a.editbutton', function(e) {
        var rowIndex = groupTable.cell($(this).parent()).index().row;
        groupID = groupTable.cell(rowIndex, 0).data();
        //alert('clicked button for id: ' + groupID);
        $("#edit_group_id").html(groupID);
        var members = groupTable.cell(rowIndex, 1).data();
        //alert("members: " + members + "\nCount: " + members.length)
        for (var i = 0; i < 6; i++) {
            var temp = i + 1;
            if (i < members.length) {
                $("#edit_group_members_" + temp).val(members[i]);
            }
            else
            {
                $("#edit_group_members_" + temp).val("");
            }
        }
        $('#modalGroupEdit').modal('show');
    });


    //show Project Delete Modal on button click
    $('#projectTable tbody').on('click', 'td a.removebutton', function(e) {
        //e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
        var rowIndex = projectTable.cell($(this).parent()).index().row;
        projectID = projectTable.cell(rowIndex, 0).data();
        $('#modalProjectDelete').modal('show');
    });

    //show Group Delete Modal on button click
    $('#groupTable tbody').on('click', 'td a.removebutton', function(e) {
        //e.stopImmediatePropagation(); // stop the row selection when clicking on an icon
        var rowIndex = groupTable.cell($(this).parent()).index().row;
        groupID = groupTable.cell(rowIndex, 0).data();
        $('#modalGroupDelete').modal('show');
    });

    //Submit Project Edit form
    $('#edit_project').submit(function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: tempProjectURL, // the url where we want to POST
            data: $('#edit_project').serialize(), // our data object
            //dataType: 'json', // what type of data do we expect back from the server
            encode: true,
            success: function(data) {
                $('#modalProjectEdit').modal('hide');
                projectTable.ajax.reload(function() {
                    $('#projectTable tbody tr:eq(0)').click();
                });
            }
        }),
        event.preventDefault();
    });

    //submit Group Edit form
    $('#edit_group').submit(function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            //the url should be projectURL + "?action=edit&" but that is not working :|
            url: groupURL + "&action=edit&", // the url where we want to POST
            data: $('#edit_group').serialize(), // our data object
            //dataType: 'json', // what type of data do we expect back from the server
            encode: true,
            success: function(data) {
                $('#modalGroupEdit').modal('hide');
                projectTable.ajax.reload(function() {
                    $('#projectTable tbody tr:eq(0)').click();
                });
            }
        }),
        event.preventDefault();
    });



    //Submit Project Delete form
    $('#buttonProjectDelete').on('click', function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: 'TestServlet?action=delete&id=' + projectID, // the url where we want to POST
            encode: true,
            success: function(data) {
                $('#modalProjectDelete').modal('hide');
                projectTable.ajax.reload(function() {
                    $('#projectTable tbody tr:eq(0)').click();
                });
            }
        }),
        event.preventDefault();
    });

    //Submit Group Delete form
    $('#buttonGroupDelete').on('click', function(event) {
        $.ajax({
            type: 'post', // define the type of HTTP verb we want to use (POST for our form)
            url: 'TestServlet?action=delete&id=' + groupID, // the url where we want to POST
            encode: true,
            success: function(data) {
                $('#modalGroupDelete').modal('hide');
                projectTable.ajax.reload(function() {
                    $('#projectTable tbody tr:eq(0)').click();
                });
            }
        }),
        event.preventDefault();
    });

    //Filtering table
    $('#button-search-table').click(function(e) {
        e.preventDefault();
        value = $('#searchtext').val();
        projectURL = "testservlet/projects" + "?session=" + $('#session').val() + "&semester=" + $('#semester').val();
        projectTable.ajax.url(projectURL).load();
        projectTable.search(value).draw();
    });


});

//Getting parameters from url
function getUrlVars()
{
	
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}