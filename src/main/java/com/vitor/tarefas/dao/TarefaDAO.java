package com.vitor.tarefas.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vitor.tarefas.models.Tarefa;
import com.vitor.tarefas.models.Usuario;
import com.vitor.tarefas.util.HibernateUtil;

public class TarefaDAO {
	public static int cadastra(Tarefa tarefa) {
		Session session = HibernateUtil.getSessinFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.save(tarefa);
		tran.commit();
		return tarefa.getCodigo();
	}
	
	public static void altera(Tarefa tarefa) {
		Session session = HibernateUtil.getSessinFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.merge(tarefa);
		tran.commit();
		
		System.out.println("Commitou isso: " + tarefa.toString());
	}
	
	public static void deleta(int codigo) {
		Tarefa tarefa = new Tarefa();
		tarefa.setCodigo(codigo);
		
		deleta(tarefa);
	}
	
	public static void deleta(Tarefa tarefa) {
		Session session = HibernateUtil.getSessinFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.delete(tarefa);
		tran.commit();
	}
	
	public static Tarefa getTarefa(int codigo) {
		return (Tarefa) HibernateUtil.getSessinFactory().openSession().get(Tarefa.class, codigo);
	}
	
	public static List<Tarefa> getTarefas(Integer where) {
		List<Tarefa> tarefas = null;
		if (where < 0) {
			tarefas = (ArrayList<Tarefa>) HibernateUtil.getSessinFactory()
					.openSession()
					.createQuery("from Tarefa")
					.list();
		} else {
			tarefas = (ArrayList<Tarefa>) HibernateUtil.getSessinFactory()
					.openSession()
					.createQuery("from Tarefa t where t.status = :status")
					.setParameter("status", where)
					.list();
		}
		
		return tarefas;
 	}
}
