
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        
        <h1>Shopping List</h1>
        <p>Hello, ${username} <a href="ShoppingList?action=logout">Logout</a></p>
        
        <h2>List</h2>
        <form action="ShoppingList?action=add" method="post">
            
            <label>Add item: </label>
            <input type="text" name="newItem" >
            <input type="submit" name="add" value="Add">
            
        </form>
        
            <c:if test="${itemList.size() > 0}">
                
                <form action="ShoppingList?action=delete" method="post">
                    
                    <c:forEach var="thisItem" items="${itemList}">

                            <input type="radio" name="toBeDeleted" value="${thisItem}">
                            <label>${thisItem}</label>
                            <br>

                    </c:forEach>
                            
                            <br>
                            
                    <input type="submit" name="delete" value="Delete">
                </form>
                
            </c:if>
        
        
        <label>${message}</label>
        
    </body>
</html>
