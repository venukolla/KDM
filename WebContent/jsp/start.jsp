<%@page import="com.ibm.cloudoe.samples.Stemming"%>
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

<%-- Before stemming:<%=request.getParameter("input") %>
After stemming: <%= Stemming.stem(request.getParameter("input")) %> --%>
Parts Of Speech of word :
<table>
<th> word</th><th> Parts of Speech</th>
<tr>
<c:forEach items="${POSobject}" var = "posjet"><br>
 <br><td>${posjet.key}</td> <c:forEach items="${posjet.value }" var = "itm" varStatus="loopings">
<td>${itm} </td></c:forEach> 
</c:forEach>

</tr>
</table>
<br><h>Best Parts of Speech:</h><br>
<c:forEach items="${POSBobject}" var = "posjet1"><br>
Best Parts Of Speech of word "${posjet1.key}": <c:forEach items="${posjet1.value }" var = "itm1" varStatus="loopings"><br>
${itm1} </c:forEach> 
</c:forEach>

<br><h> Synoniums of words:</h><br>

<c:forEach items="${POSHobject}" var = "posjet2"><br>
Synonyms of word "${posjet2.key}": <c:forEach items="${posjet2.value }" var = "itm2" varStatus="loopings"><br>
${itm2} </c:forEach> 
</c:forEach>

<br><h>Hypo Synonyms of words:</h><br>


<c:forEach items="${POSHSobject}" var = "posjet3"><br>
Synonyms of word "${posjet3.key}": <c:forEach items="${posjet3.value }" var = "itm3" varStatus="loopings"><br>
${itm3} </c:forEach> 
</c:forEach>
<% } %>