package br.com.test.rf.agendaTransf.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.test.rf.agendaTransf.domain.Agente;


public class GeraTabelas {

	public static void main(String[] args) {
		Agente agente = new Agente();
		agente.setCpfCnpj("12345678");
		agente.setNome("Teste");
		agente.setEmail("teste@teste.com");
		
		Configuration cfg = new Configuration().configure();
//		AnnotationConfiguration cfg = new AnnotationConfiguration();
//		cfg.addAnnotatedClass(Agente.class);
//		new SchemaExport(cfg).create(true, true); 
//		agente.getPersister().save();
		
		SessionFactory sF = cfg.buildSessionFactory();
        Session session = sF.openSession();
        session.beginTransaction();
        session.saveOrUpdate(agente);
        session.getTransaction().commit();
        
        
	}
}
