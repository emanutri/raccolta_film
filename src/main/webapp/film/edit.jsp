<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../header.jsp" />
		<title>modifica elemento</title>
		
		<!-- style per le pagine diverse dalla index -->
	    <link href="./assets/css/global.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica Film</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
					<form method="post" action="ExecuteModificaFilmServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>titolo <span class="text-danger">*</span></label>
								<input type="text" name="titolo" id="titolo" class="form-control" value="${film_attribute.titolo}">
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Genere <span class="text-danger">*</span></label>
								<input type="text" name="genere" id="genere" class="form-control" value="${film_attribute.genere}">
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>durata <span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="durata" id="durata" value="${film_attribute.minutiDurata}">
							</div>
							
							<div class="form-group col-md-3">
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${film_attribute.dataPubblicazione}' />
								<label>Data Pubblicazione <span class="text-danger">*</span></label>
                        		<input class="form-control" id="dataPubblicazione" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataPubblicazione" required 
                            		value="${parsedDate}" >
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label for="regista.id">Regista</label>
							    <select class="form-control" id="regista.id" name="regista.id">
							    	<option value="" selected> -- Selezionare una voce -- </option>
							      	<c:forEach items="${list_reg_attr }" var="registaItem">
							      		<option value="${registaItem.id}" ${film_attribute.regista.id == registaItem.id?'selected':''} >${registaItem.nome } ${registaItem.cognome }</option>
							      	</c:forEach>
							    </select>
							</div>
						</div>
						<p>
				 
						<div class="form-group col-md-6">	
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							<input type="hidden" name="idFilm" value="${film_attribute.id}">
							 <a href="ExecuteListFilmServlet" class='btn btn-outline-secondary' style='width:80px'>
			                <i class='fa fa-chevron-left'></i> Back
			      			  </a>
						</div>
	
					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	</body>
</html>