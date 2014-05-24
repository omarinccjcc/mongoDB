<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <h2>Here is a simple CRUD using Spring MVC and MongoDB.</h2>

		
        <form action="person/save" method="post">
            <input type="hidden" name="id">
			
			<table>
				<tr>
					<td>firstName: <td>
					<td><input type="text" id="firstName" name="firstName"/><td>
				<tr>
				<tr>
					<td>secondName: <td>
					<td><input type="text" id="secondName" name="secondName"/><td>
				<tr>
				<tr>
					<td>age: <td>
					<td><input type="text" id="age" name="age"/><td>
				<tr>
			<table>


            <input type="submit" value="Submit"/>
        </form>
 
	<br><br>
	
<!--    <table border="1">-->
<!--        <c:forEach var="person" items="${personList}">-->
<!--            <tr>-->
<!--                <td>${person.firstName}</td>-->
<!--				<td>${person.secondName}</td>-->
<!--				<td>${person.age}</td>-->
<!--				<td><input type="button" value="delete" onclick="window.location='person/delete?id=${person.id}'"/></td>-->
<!--            </tr>-->
<!--        </c:forEach>-->
<!--    </table>-->
     

    <table border="1">
        <c:forEach var="meter" items="${meterList}">
            <tr>
                <td>${meter.energyUsageId}</td>
				<td>${meter.accountProductServiceDetailId}</td>
				<td>${meter.totalUsage}</td>
				<td>${meter.previousMsmt}</td>
				<td>${meter.currentMsmt}</td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>