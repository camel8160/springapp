package com.marketingpersonal.model.dao;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketingpersonal.model.entity.Jefatura;

/**
 * Clase DAO encargada de realizar la gesti�n de la base de datos para Jefaturas
 * @author Jarrison Garcia, Juan Camilo Monsalve 
 * @date 30/10/2018
 */
@Repository
public class JefaturaDAO implements IJefaturaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
     * M�todo que permite almacenar la informaci�n de una Jefatura en la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Jefatura a almacenar
     */
	public void addJefatura(Jefatura entity) {
		Session session = getSessionFactory().getCurrentSession();
		entity.setNombre(WordUtils.capitalizeFully(entity.getNombre()).trim());
		session.save(entity);
	}

	/**
     * M�todo que permite eliminar la informaci�n de una Jefatura de la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Jefatura a eliminar
     */
	public void deleteJefatura(Jefatura entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	/**
     * M�todo que permite actualizar la informaci�n de una Jefatura en la base de datos
     * @param entity variable que contiene la informaci�n de la Jefatura a actualizar
     */
	public void updateJefatura(Jefatura entity) {
		Session session = getSessionFactory().getCurrentSession();
		entity.setNombre(WordUtils.capitalizeFully(entity.getNombre()).trim());
		session.update(entity);
	}

	/**
     * M�todo que permite consultar la informaci�n de una Jefatura a partir del id
     * @param id: variable que contiene el id de la Jefatura a consultar
     * @return Jefatura: variable que contiene la informaci�n de la Jefatura a retornar
     */
	public Jefatura getJefaturaById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Jefatura) session
				.createQuery("from Jefatura where id=?").setParameter(0, id)
				.uniqueResult();
	}

	/**
     * M�todo que permite consultar la informaci�n de las Jefaturas almacenadas en la base de datos
     * @param activo: variable que indica si se consultaran solo Jefaturas activas o todos
     * @return List<Jefatura>: variable que contiene la informaci�n de las Jefaturas consultadas
     */
	public List<Jefatura> getJefaturas(boolean activo) {
		Session session = getSessionFactory().getCurrentSession();
		if(activo) {
			return (List<Jefatura>) session.createQuery("from Jefatura where estado = true").list();
		}else {
			return (List<Jefatura>) session.createQuery("from Jefatura").list();
		}
	}

}
