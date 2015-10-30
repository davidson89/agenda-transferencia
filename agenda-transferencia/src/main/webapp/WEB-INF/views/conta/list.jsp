<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css" media="screen">
	@import url("<c:url value="/resources/styles/style.css"/>");
	</style>
	<title>Contas</title>
</head>
<body>
<%-- 	<form:errors path="item.descricao" cssStyle="color:red" /> --%>
		<%@ include file="/menu.jsp"%>
		<div id="main">
			<div id="body">
				<h1>Pesquisa de Conta</h1>
				<form action="buscaConta" method="POST">
					<table width="100%" class="consulta">
						<tr style="border: 1px solid #000000;">
							<td width="5%" class="consulta" align="left"><b>Cliente:</b></td>
							<td width="20%" class="consulta" align="left"><input type="text" id="clienteName" name="clienteName" size="20"/></td>
							<td width="5%" class="consulta" align="left"><b>CPF/CNPJ:</b></td>
							<td width="20%" class="consulta" align="left"><input type="text" id="cpfCnpj" name="cpfCnpj" size="15"/></td>
							<td width="10%" class="consulta" align="left"><b>Número Conta:</b></td>
							<td width="20%" class="consulta" align="left"><input type="text" id="numeroConta" name="numeroConta" size="10"/></td>
							<td width="20%" class="consulta" align="right" >
								<button onclick="window.location.href='buscaConta'" name="Pesquisar" style="width: 100px;height: 30px">Pesquisar</button>
							</td>
						</tr>
					</table>
				</form>
				<table align="center" width="100%">
					<tr>
						<td width="5%" align="center"><b>Id</b></td>
						<td width="25%" align="center"><b>Cliente</b></td>
						<td width="25%" align="center"><b>CPF/CNPJ</b></td>
						<td width="20%" align="center"><b>Número Conta</b></td>
						<td width="10%" align="center"><b>Saldo</b></td>
						<td width="10%" align="center"><b>Limíte</b></td>
						<td width="5%" align="center"><b>Ação</b></td>
					</tr>
					<c:forEach items="${contas}" var="conta" varStatus="contador">
						<tr id="row_${conta.id}" align="center" bgcolor="#${contador.count % 2 == 0 ? '99FFFF' : 'FFFF99' }">
							<td>${conta.id}</td>
							<td>${conta.agente.nome}</td>
							<td>${conta.agente.cpfCnpj}</td>
							<td>${conta.numeroConta}</td>
							<td>${conta.saldo}</td>
							<td>${conta.limite}</td>
							<td>
								<a href="remove?id=${conta.id}"><img src="<c:url value="/resources/imagens/delete.png"/>" style="height: 21px; width: 24px;" /></a>
							</td> 
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
</body>
</html>