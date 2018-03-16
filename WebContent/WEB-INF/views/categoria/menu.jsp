<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/categoria/salvar" var="urlSalvarCategoria" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main>
		<h1>Categorias de Ocorrência</h1>
		<section id="sectionCategorias" class="container">
			<h2>Nova Categoria</h2>
			<form:form action="${urlSalvarCategoria}" method="post" modelAttribute="categoria">
				<label>
					Nome
					<form:input path="nome" type="text"/>
				</label>
				<button type="submit" class="btn">SALVAR</button>
			</form:form>
			
			<%-- Tabela de ocorrências --%>
			<table id="tabelaOcorrencias" class="table container read-container ma-t-l">
				<thead>
					<tr>
						<th>Categorias</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ocorrencias}" var="ocorrencia">
					
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>