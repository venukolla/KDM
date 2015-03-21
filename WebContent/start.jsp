<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form name="para_form" action="./TestingNLP" target="_balnk">
<label for="para"> Enter your text :</label>

<input type="text" name="input" id="input" class="p" />
<input type="submit" value="Submit" class="submitbutton" />

</form>

<%if(request.getParameter("input") !=null){ %>
<br>
<c:forEach items="${POSobject}" var = "posjet"><br>
 Parts Of Speech of word ${posjet.key} : <c:forEach items="${posjet.value }" var = "itm" varStatus="loopings"><br>
${itm} ,</c:forEach> 

Values=${posjet}
</c:forEach>
<% } %>