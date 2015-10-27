<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width-device-width, initial-scale=1.0">
<title>SUST Archives</title>
<link href="<c:url value="/resources/css/topbar.css" />"
	rel="stylesheet" type="text/css" />

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />"
	rel="stylesheet" type="text/css" />
<!-- <link href="<c:url value="/resources/css/thesis-list.css"/>"
	rel="stylesheet" type="text/css" />  -->

<script
	src="<c:url value="/resources/javascript/jquery-1.11.3.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/javascript/bootstrap.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/javascript/course.js" />"
	type="text/javascript"></script>

</head>
<body>
</body>
<nav class="navbar navbar-default navbar-fixed-top">
<div class="row topbar">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div
		class="
                 col-sm-4 col-sm-offset-1
                 col-xs-12">
		<a class="navbar-brand" href="project-groups.html">SUST Archives<sup>beta</sup></a>
	</div>
	<div
		class="
                 col-sm-5 col-sm-offset-1
                 col-xs-12">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">Profile</a></li>
			<li><a href="#">Settings</a></li>
			<li><a href="sign-in.html">Log out</a></li>
		</ul>
	</div>
</div>
</nav>
<div class="container">
	
	<div>
		<table id="course_table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Course ID</th>
					<th>Course Code</th>
					<th>Course Title</th>
					<th>Course Credit</th>
				</tr>
			</thead>

			<tbody>
				<c:if test="${not empty courses}">
					<c:forEach var="course" items="${courses}">
						<tr>
							<td>${course.getCourseId()}</td>
							<td>${course.getCourseCode()}</td>
							<td>${course.getCourseTitle()}</td>
							<td>${course.getCredit()}</td>
						</tr>
						
					</c:forEach>

				</c:if>
			</tbody>
		</table>
	</div>
	<a href="<c:url value="/"/>">Index Page</a>
</html>