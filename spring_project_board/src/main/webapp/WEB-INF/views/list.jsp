<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table width="680" cellpadding="0" cellspacing="0" border="1">
		<tr align = "center">
			<td width="50">번호</td>
			<td width="80">이름</td>
			<td width="300">제목</td>
			<td width="200">날짜</td>
			<td width="50">히트</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td align="center">${dto.bId}</td>
			<td align="center">${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">&nbsp&nbsp</c:forEach>
				<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
			<td align="center">${dto.bDate}</td>
			<td align="center">${dto.bHit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view">글작성</a> </td>
		</tr>
	</table>

</body>
</html>