<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class=content>
	<div id="main">
		<h1>Pesquisa de Conta</h1>
		<form action="buscaContaOuVaiParaForm" method="POST">
			<table class="consulta" style="border: 1px solid #8B8682;">
				<tr>
					<td width="5%" class="consulta" align="left"><b>Cliente:</b></td>
					<td width="20%" class="consulta" align="left"><input
						type="text" id="nomeCliente" name="nomeCliente" size="20" /></td>
					<td width="5%" class="consulta" align="left"><b>CPF/CNPJ:</b></td>
					<td width="20%" class="consulta" align="left"><input
						type="text" id="cpfCnpj" name="cpfCnpj" size="15" /></td>
					<td width="10%" class="consulta" align="left"><b>Número
							Conta:</b></td>
					<td width="20%" class="consulta" align="left"><input
						type="text" id="numeroConta" name="numeroConta" size="10" /></td>
				</tr>
			</table>
			<div style="text-align: right; max-height: 30px;">
				<button type="submit" name="submit" value="pesquisa"
					style="width: 100px; height: 30px; margin-top: 5px;">Pesquisar</button>
				<button type="submit" name="submit" value="novo"
					style="width: 100px; height: 30px; margin-top: 5px;">Novo</button>
			</div>
		</form>
		<table class="resultado">
			<tr>
				<td width="5%" align="center"><b>Id</b></td>
				<td width="25%" align="center"><b>Cliente</b></td>
				<td width="25%" align="center"><b>CPF/CNPJ</b></td>
				<td width="20%" align="center"><b>Número Conta</b></td>
				<td width="10%" align="center"><b>Saldo</b></td>
				<td width="10%" align="center"><b>Limíte</b></td>
				<td width="5%" align="center"><b>Ação</b></td>
			</tr>
			<c:forEach items="${contas}" var="conta">
				<tr id="row_${conta.id}" align="center">
					<td>${conta.id}</td>
					<td>${conta.agente.nome}</td>
					<td>${conta.agente.cpfCnpj}</td>
					<td>${conta.numeroConta}</td>
					<td>${conta.saldo}</td>
					<td>${conta.limite}</td>
					<td><a href="removeConta?id=${conta.id}"><img
							src="<c:url value="/resources/imagens/delete.png"/>"
							style="height: 21px; width: 24px;" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>