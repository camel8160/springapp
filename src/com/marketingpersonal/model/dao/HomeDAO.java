package com.marketingpersonal.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketingpersonal.model.entity.Home;

/**
 * Clase DAO encargada de realizar la gesti�n de la base de datos para las Variables Macroeconomicas
 * @author Jarrison Garcia, Juan Camilo Monsalve 
 * @date 30/10/2018
 */
@Repository
public class HomeDAO implements IHomeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
     * M�todo que permite almacenar la informaci�n de una Variable Macroeconomica en la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Variable Macroeconomica a almacenar
     */
	public void addHome(Home entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	/**
     * M�todo que permite eliminar la informaci�n de una Variable Macroeconomica de la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Variable Macroeconomica a eliminar
     */
	public void deleteHome(Home entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	/**
     * M�todo que permite actualizar la informaci�n de una Variable Macroeconomica en la base de datos
     * @param entity variable que contiene la informaci�n de la Variable Macroeconomica a actualizar
     */
	public void updateHome(Home entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	/**
     * M�todo que permite consultar la informaci�n de una Variable Macroeconomica a partir del id
     * @param id: variable que contiene el id de la Variable Macroeconomica a consultar
     * @return Home: variable que contiene la informaci�n de la Variable Macroeconomica a retornar
     */
	public Home getHomeById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Home) session
				.createQuery("from Home where id=?").setParameter(0, id)
				.uniqueResult();
	}

	/**
     * M�todo que permite consultar la informaci�n de las Variables Macroeconomicas almacenadas en la base de datos
     * @param activo: variable que indica si se consultaran solo Variables Macroeconomicas activas o todos
     * @return List<Home>: variable que contiene la informaci�n de las Variables Macroeconomicas consultadas
     */
	public List<Home> getHomes(boolean activo) {
		Session session = getSessionFactory().getCurrentSession();
		if(activo) {
			return (List<Home>) session.createQuery("from Home where estado = true and CURRENT_TIMESTAMP between fechaInicio and fechaFin").list();
		}else {
			return (List<Home>) session.createQuery("from Home").list();
		}
	}

}
