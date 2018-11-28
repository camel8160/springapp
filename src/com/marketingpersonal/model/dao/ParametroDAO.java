package com.marketingpersonal.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marketingpersonal.model.entity.Parametro;

/**
 * Clase DAO encargada de realizar la gesti�n de la base de datos para Parametros
 * @author Jarrison Garcia, Juan Camilo Monsalve 
 * @date 30/10/2018
 */
@Repository
public class ParametroDAO implements IParametroDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
     * M�todo que permite almacenar la informaci�n de un Parametro en la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Parametro a almacenar
     */
	public void addParametro(Parametro entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	/**
     * M�todo que permite eliminar la informaci�n de un Parametro de la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Parametro a eliminar
     */
	public void deleteParametro(Parametro entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	/**
     * M�todo que permite actualizar la informaci�n de un Parametro en la base de datos
     * @param entity variable que contiene la informaci�n de la entidad Parametro a actualizar
     */
	public void updateParametro(Parametro entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	/**
     * M�todo que permite consultar la informaci�n de un Parametro a partir del id
     * @param id: variable que contiene el id del Parametro a consultar
     * @return Parametro: variable que contiene la informaci�n del Parametro a retornar
     */
	public Parametro getParametroById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Parametro) session
				.createQuery("from Parametro where id=?").setParameter(0, id)
				.uniqueResult();
	}

	/**
     * M�todo que permite consultar la informaci�n del los Parametros almacenadss en la base de datos
     * @return List<Parametro>: variable que contiene la informaci�n de los Parametros consultados
     */
	public List<Parametro> getParametros() {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Parametro>) session.createQuery("from Parametro").list();
	}

	/**
     * M�todo que permite consultar la informaci�n de un Parametro a partir del codigo
     * @param codigoParametro: variable que contiene el codigo del Parametro a consultar
     * @return Parametro: variable que contiene la informaci�n del Parametro a retornar
     */
	public Parametro getParametroByCodigo(String codigoParametro) {
		Session session = getSessionFactory().getCurrentSession();
		return (Parametro) session.createQuery("from Parametro where codigo = :codigo")
				.setParameter("codigo", codigoParametro)
				.uniqueResult();
	}

}
