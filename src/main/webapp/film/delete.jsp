<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<jsp:include page="../header.jsp" />
	<link href="./assets/css/global.css" rel="stylesheet">
		
	<title>Elimina film</title>
	
</head>

<body>

	<jsp:include page="../navbar.jsp" />

	
	<main role="main" class="container">
		  <div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
			  ${successMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			  	<span aria-hidden="true">&times;</span>
			  </button>
		  </div>
		  
			<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio da eliminare
		    </div>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Titolo:</dt>
				  <dd class="col-sm-9">${film_delete.titolo}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Genere:</dt>
				  <dd class="col-sm-9">${film_delete.genere}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data di pubblicazione:</dt>
				  <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${film_delete.dataPubblicazione}" />  		    	
				</dl>
				
				<dl class="row">
				  <dt class="col-sm-3 text-right">Durata</dt>
				  <dd class="col-sm-9">${film_delete.minutiDurata}</dd>
		    	</dl>
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <form action="ExecuteDeleteFilmServlet" method="post">
			       <a href="ExecuteListFilmServlet" class='btn btn-outline-secondary' style='width:7em'>
		             <i class='fa fa-chevron-left'></i> Indietro
		           </a>
			       	<input type="hidden" name="idDeleteInput" value="${film_delete.id}">
		        	<button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger" style='width:7em;'>Elimina</button>
		        </form>
		       
		    </div>
		    
		</div>	
		
	</main>
	
	<jsp:include page="../footer.jsp" />

</body>

</html>