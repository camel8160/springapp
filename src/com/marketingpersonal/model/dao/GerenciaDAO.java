package com.marketingpersonal.model.dao;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketingpersonal.model.entity.Gerencia;

/**
 * Clase DAO encargada de realizar la gesti�n de la base de datos para Gerencias
 * @author Jarrison Garcia, Juan Camilo Monsalve 
 * @date 30/10/2018
 */
@Repository
public class GerenciaDAO implements IGerenciaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
     * M�todo que permite almacenar la informaci�n de una Gerencia en la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Gerencia a almacenar
     */
	public void addGerencia(Gerencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		entity.setNombre(WordUtils.capitalizeFully(entity.getNombre()).trim());
		session.save(entity);
	}

	/**
     * M�todo que permite eliminar la informaci�n de una Gerencia de la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Gerencia a eliminar
     */
	public void deleteGerencia(Gerencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	/**
     * M�todo que permite actualizar la informaci�n de una Gerencia en la base de datos
     * @param entity variable que contiene la informaci�n de la Gerencia a actualizar
     */
	public void updateGerencia(Gerencia entity) {
		Session session = getSessionFactory().getCurrentSession();
		entity.setNombre(WordUtils.capitalizeFully(entity.getNombre()).trim());
		session.update(entity);
	}

	/**
     * M�todo que permite consultar la informaci�n de una Gerencia a partir del id
     * @param id: variable que contiene el id de la Gerencia a consultar
     * @return Gerencia: variable que contiene la informaci�n de la Gerencia a retornar
     */
	public Gerencia getGerenciaById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Gerencia) session
				.createQuery("from Gerencia where id=?").setParameter(0, id)
				.uniqueResult();
	}

	/**
     * M�todo que permite consultar la informaci�n de las Gerencias almacenadas en la base de datos
     * @param activo: variable que indica si se consultaran solo Gerencias activas o todos
     * @return List<Gerencia>: variable que contiene la informaci�n de las Gerencias consultadas
     */
	public List<Gerencia> getGerencias(boolean activo) {
		Session session = getSessionFactory().getCurrentSession();
		if(activo) {
			return (List<Gerencia>) session.createQuery("from Gerencia where estado = true").list();
		}else {
			return (List<Gerencia>) session.createQuery("from Gerencia").list();
		}
	}

}
