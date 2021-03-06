<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/izitoast/css/iziToast.min.css"/>' />
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>' />
<script
	src="<c:url value="/resources/javascript/jquery/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/javascript/popper.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<title>Home</title>
</head>
<body>

	<div id="wrapper" style="background-color: darkcyan;">

		<!-- Sidebar -->
		<jsp:include page="menu.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn btn-dark"
					href="#menu-toggle" id="menu-toggle"> <span
					class="glyphicon glyphicon-print"></span>Menu
				</a> </nav>
			</div>
		</div>

		<div class="modal-content">
			<c:if test="${!empty listaEmprestimo }">
				<h3 style="margin-left: 40%;">Lista de Empr?stimos</h3>
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">C?digo do Emprestimo</th>
							<th scope="col">T?tulo do Item</th>
							<th scope="col">Matr?cula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Data da Entrega</th>
							<th scope="col">Data da Devolu??o</th>
							<th scope="col">QTD de Renovacao</th>
							<th scope="col">Devolu??o</th>
							<th scope="col">Renovacao</th>
							<th scope="col">Entregar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaEmprestimo}" var="emprestimo">
							<tr>
								<th scope="row"><a
									href="<c:url value='/emprestimo/${emprestimo.id}' />">${emprestimo.id}</a></th>
								<td>${emprestimo.item.titulo}</td>
								<td>${emprestimo.aluno.matricula}</td>
								<td>${emprestimo.aluno.nome}</td>
								<td>${emprestimo.dataCadastrado}</td>
								<td>${emprestimo.dataDevolucao}</td>
								<td>${emprestimo.renovacao}</td>
								<c:if test="${emprestimo.entregou == true}">
									<td>sim</td>
								</c:if>
								<c:if test="${emprestimo.entregou == false}">
									<td>nao</td>
								</c:if>
								<c:if test="${emprestimo.entregou == false}">
									<td><a style="color: red;"
										href="<c:url value='/emprestimo/renovar/${emprestimo.id}' />">
											renovar </a></td>
								</c:if>
								<c:if test="${emprestimo.entregou == false}">
									<td><a style="color: red;"
										href="<c:url value='/emprestimo/entregar/${emprestimo.id}' />">
											entrega </a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${!empty listaDivida }">
				<h3 style="margin-left: 40%;">Lista de Dividas</h3>
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">C?digo de Divida</th>
							<th scope="col">T?tulo do Item</th>
							<th scope="col">Matr?cula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Valor</th>
							<th scope="col">Pagou</th>
							<th scope="col">Pagamento</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaDivida}" var="divida">
							<tr>
								<th scope="row">${divida.id}</th>
								<td>${divida.emprestimo.item.titulo}</td>
								<td>${divida.aluno.matricula}</td>
								<td>${divida.aluno.nome}</td>
								<td>${divida.saldo}</td>
								<c:if test="${divida.pago == true}">
									<td>sim</td>
								</c:if>
								<c:if test="${divida.pago == false}">
									<td>nao</td>
								</c:if>
								<td><a style="color: red;"
									href="<c:url value='/divida/pagar/${divida.id}' />"> pagar
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript">
		var mensagem = '${mensagem}';
		if (mensagem != "") {
			iziToast.show({
				title : 'Erro',
				message : mensagem,
				color : 'red',
				timeout : false,
				position : 'topRight'
			});
		}
	</script>
	<script type="text/javascript"
		src="<c:url value="/resources/javascript/action.js" />"></script>
</body>
</html>
