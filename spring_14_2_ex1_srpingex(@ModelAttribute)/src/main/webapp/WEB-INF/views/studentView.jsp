<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!--원래 커맨드의 객체의 이름은 studentInformation 였지만
	객체 이름이 길기에  ModelAttribute 어노테이션을  이용하여 객체 이름은
	studentInfo로 변경했음-->
	
이름 : ${studentInfo.name} <br />
나이 : ${studentInfo.age} <br />
학년 : ${studentInfo.classNum} <br />
반 : ${studentInfo.gradeNum}
</body>
</html>