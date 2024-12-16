<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/udpatec.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="head">
        <h1>SISCO UNIVERSITY</h1>
    </div>
    <div class="crd">
        <form action="StudenController" method="GET">
            <h2>Update Details</h2>
            
            <input type="hidden" name="command" value="UPDATE"/>
            <input type="hidden" name="studentId" value="${studentData.id}"/>
            <label>First Name</label><br>
            <input type="text"  name="firstname" value="${studentData.firstname}" class="inps"/><br>
            <br>
            <label>last Name</label><br>
            <input type="text"  name="lastname" value="${studentData.lastname}"  class="inps"/><br>
            <br>
            <label>Email</label><br>
            <input type="text"  name="email" value="${studentData.email}" class="inps"/><br>
            <br>
            <input type="submit" value="Update">
            <br>
            
            
            
        
        </form>
        <a href="StudenController">return to list</a>
    </div>

   
</body>
</html>