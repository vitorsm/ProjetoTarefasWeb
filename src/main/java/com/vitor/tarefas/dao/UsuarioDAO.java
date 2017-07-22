package com.vitor.tarefas.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vitor.tarefas.models.Usuario;
import com.vitor.tarefas.util.HibernateUtil;

public class UsuarioDAO {
	public static int cadastra(Usuario usuario) {
		Session session = HibernateUtil.getSessinFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.save(usuario);
		tran.commit();
		return usuario.getCodigo();
	}
	
	public static void altera(Usuario usuario) {
		Session session = HibernateUtil.getSessinFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.merge(usuario);
		tran.commit();
	}
	
	public static void deleta(Usuario usuario) {
		Session session = HibernateUtil.getSessinFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.delete(usuario);
		tran.commit();
	}
	
	public static Usuario getUsuario(int codigo) {
		return (Usuario) HibernateUtil.getSessinFactory().openSession().get(Usuario.class, codigo);
	}
	
	public static List<Usuario> getUsuarios(String where) {
		List<Usuario> usuarios = (ArrayList<Usuario>) HibernateUtil.getSessinFactory()
				.openSession()
				.createQuery("FROM Usuario " + where)
				.list();
		
		return usuarios;
 	}
	
	public static Usuario autenticaUsuario(String userName, String senha) {
		Usuario usuario = (Usuario) HibernateUtil.getSessinFactory()
				.openSession()
				.createQuery("FROM Usuario WHERE UserName = userName AND Senha = senha")
				.setString("login", "aluno")
				.uniqueResult();

		return usuario;
	}
}
