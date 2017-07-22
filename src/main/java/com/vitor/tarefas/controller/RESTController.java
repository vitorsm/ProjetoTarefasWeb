package com.vitor.tarefas.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitor.tarefas.dao.TarefaDAO;
import com.vitor.tarefas.dao.UsuarioDAO;
import com.vitor.tarefas.models.Tarefa;
import com.vitor.tarefas.models.TarefaTemp;
import com.vitor.tarefas.models.Usuario;

@RestController
public class RESTController {

	@RequestMapping(
			value = "usuarios", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Usuario>> getUsuarios() {
		Collection<Usuario> usuarios = UsuarioDAO.getUsuarios("");
		
		return new ResponseEntity<Collection<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "tarefas", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Tarefa>> getTarefas() {
//		List<Tarefa> teste = TarefaDAO.getTarefas(-1);
//		
//		byte[] foto = teste.get(0).getFoto();
//		String str = "";
//		for (int i = 0; i < foto.length; i++) {
//			str += foto[i];
//		}
//		System.out.println("FOTO: " + str);
		
		Collection<Tarefa> tarefas = TarefaDAO.getTarefas(-1);
		
		return new ResponseEntity<Collection<Tarefa>>(tarefas, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "tarefasPend", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Tarefa>> getTarefasPend() {
		Collection<Tarefa> tarefas = TarefaDAO.getTarefas(Tarefa.STATUS_PENDENTE);
		
		return new ResponseEntity<Collection<Tarefa>>(tarefas, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "tarefasConc", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Tarefa>> getTarefasConc() {
		Collection<Tarefa> tarefas = TarefaDAO.getTarefas(Tarefa.STATUS_CONCLUIDA);
		
		return new ResponseEntity<Collection<Tarefa>>(tarefas, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "login", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> autenticaUsuario() {
		String userName = "";
		String senha = "";
		
		Usuario usuario = UsuarioDAO.autenticaUsuario(userName, senha);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "cadTarefa/{nome}", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tarefa> cadastraTarefa(@PathVariable("nome") String nome) {
		System.out.println("VEIOOOOOOOOOOOOOOOO ISOOOOOOOOOOOOOOOOOOOOO : " + nome);
		Tarefa t = new Tarefa();
		t.setNome(nome);
		t.setStatus(Tarefa.STATUS_PENDENTE);
	
		TarefaDAO.cadastra(t);
		
		return new ResponseEntity<Tarefa>(t, HttpStatus.OK);
	}
	
//	//antes era PUT
//	@RequestMapping(
//			value = "editaTarefa", 
//			method = RequestMethod.POST,
//			consumes = MediaType.APPLICATION_JSON_VALUE, 
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Tarefa> editaTarefa(@RequestBody Tarefa tarefa) {
//		TarefaDAO.altera(tarefa);
//		
//		System.out.println("Recebeu isoooooo: " + tarefa.toString());
//		return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
//	}
	
	//antes era PUT
	@RequestMapping(
			value = "editaTarefa", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tarefa> editaTarefa(@RequestBody TarefaTemp t) {
//		System.out.println(str);
//		try {
//			JSONObject j = new JSONObject(str);
//			String fotoStr = j.getString("foto");
//			byte foto[] = Base64.decodeBase64(str);
//			String teste = "";
//			for (int i = 0; i < foto.length; i++) {
//				teste+= foto[i];
//			}
//			
//			System.out.println("Foto> " + teste);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		
		Tarefa tarefa = t.getTarefa();
//		
//		byte[] foto = tarefa.getFoto();
//		String str = "";
//		for (int i = 0; i < foto.length; i++) {
//			str += foto[i];
//		}
//		System.out.println("FOTO: " + tarefa.getFoto());
		
		TarefaDAO.altera(tarefa);
		
		return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "deletaTarefa/{codigo}", 
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tarefa> deletaTarefa(@PathVariable("codigo") int codigo, @RequestBody Tarefa tarefa) {
		TarefaDAO.deleta(codigo);
		
		return new ResponseEntity<Tarefa>(HttpStatus.NO_CONTENT);
	}
}
