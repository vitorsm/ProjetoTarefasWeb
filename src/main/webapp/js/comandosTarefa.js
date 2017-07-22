/**
 * 
 */

var $tarefasAbertos = [];
var $tarefasFechados = [];

function abrirTarefa($id) {
	$($id).css("opacity","1");
	$($id).css("width","100%");
}

function carregarHtml ($idDiv, $codigoHtml){
	$($idDiv).css("display","block");
	$($idDiv).html($codigoHtml);
}
function carregarPaginaComForm ($idDiv, $paginaCarregar){
	$matricula = document.getElementById('matricula').value;
	$cpf = document.getElementById('cpf').value;
	$email = document.getElementById('email').value;
	$paginaCarregar = $paginaCarregar + "&matricula=" + $matricula + "&cpf=" + $cpf + "&email=" + $email + "";
	carregarPagina($idDiv, $paginaCarregar);
}
function carregarPagina ($idDiv, $paginaCarregar){
	$($idDiv).html('<img src="../imagens/carregando.gif">');
	$($idDiv).load($paginaCarregar);
}
function abrirListartarefa ($id) {
	alert("id " + $id);
	var $fechado = true;
	var $aberto = false;
	
	for ($i = 0 ; $i < $tarefasAbertos.length ; $i++) {
		if ($id == $tarefasAbertos[$i]){
			$fechado = false;
		}
	}
	for ($i = 0 ; $i < $tarefasFechados.length ; $i++) {
		if ($id == $tarefasFechados[$i]){
			$aberto = true;
		}
	}
	if ($fechado) {
		abrirTarefa($id);
		$tarefasAbertos[$tarefasAbertos.length] = $id;
		for ($i = 0 ; $i < $tarefasFechados.length ; $i++) {
			if ($id == $tarefasFechados[$i]){
				$tarefasFechados[$i] = "";
			}
		}
	} else {
		if ($aberto) {
			for ($i = 0 ; $i < $tarefasAbertos.length ; $i++) {
				if ($id == $tarefasAbertos[$i]){
					$tarefasAbertos[$i] = "";
				}
			}
		}
	}
	
}
function fecharListartarefa ($idDiv){
	$tarefasFechados[$tarefasFechados.length] = $idDiv;
	$($idDiv).css("opacity","0");
	$($idDiv).css("width","0%");
}
