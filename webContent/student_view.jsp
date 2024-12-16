<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/list.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="main" >

    <div id="head">
        <div class="univ"><h1>SISCO UNIVERSITY</h1></div>
        <button id="add" onclick="window.location.href='add_student.jsp'">ADD</button></div>
    <div id="tble">
        <table class="table rad">
            <thead class="table-dark">
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Options</th>
            </thead>
            <tbody>
                <c:forEach var="temp" items="${Data}">
                <c:url var="updateLink" value="StudenController" >
                	<c:param name="command" value="LOAD"/>
                	<c:param name="studentId" value="${temp.id}"/>
                </c:url>
                
                 <c:url var="deleteLink" value="StudenController" >
                	<c:param name="command" value="DELETE"/>
                	<c:param name="studentId" value="${temp.id}"/>
                </c:url>
			<tr>
			
				<td>${temp.id}</td>
				<td>${temp.firstname}</td>
				<td>${temp.lastname}</td>
				<td>${temp.email}</td>
				<td><button onclick="window.location.href='${updateLink}'" id="upd">Update</button>|
					<button onclick="window.location.href='${deleteLink}'" onclick="if(!(confirm('Are you sure want to delete this student?')))return false" id="dlt">Delete</button>
				</td>
				
			
			</tr>
		
		
		
		</c:forEach>
        </tbody>
        </table>
    </div>
</body>
</html>