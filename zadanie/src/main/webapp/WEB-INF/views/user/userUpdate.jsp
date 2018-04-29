<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>User update</title>
</head>
<body>

	<form:form method="post" modelAttribute="user">
		<p>
			User Nickname
			<form:input path="nickname" type="text" />
			<form:errors path="nickname"></form:errors>
		</p>
		<%--<form:select path="publisher">
			<form:options items="${availablePublishers}" itemValue="id"
				itemLabel="name" />
		</form:select>
		<form:errors path="publisher" />
		<br /> --%>
		<p>
			Password
			<form:password path="password" />
			<form:errors path="password"></form:errors>
		</p>
		<p>
			<input type="submit" />
		</p>
	</form:form>

</body>
</html>