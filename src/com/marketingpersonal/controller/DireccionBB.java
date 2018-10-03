package com.marketingpersonal.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.marketingpersonal.common.Util;
import com.marketingpersonal.model.entity.Direccion;
import com.marketingpersonal.service.IDireccionService;

@ManagedBean(name = "direccionBB")
@ViewScoped
public class DireccionBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDireccionService direccionService;
	private Util util;
	private Direccion direccion;
	private Direccion selectedDireccion;
	private List<Direccion> listaDirecciones;
	
	public DireccionBB() {
		util = Util.getInstance();
		direccion = new Direccion();
		selectedDireccion = new Direccion();
		listaDirecciones = getDireccionService().getDirecciones(false);
	}
	
	private boolean validar(Direccion dir) {
		boolean permiteGuardar = true;
		
		if(dir.getNombre() == null ||"".equals(dir.getNombre().trim())) {
			util.mostrarError("El campo Nombre es requerido.");
			permiteGuardar = false;
		}
		
		return permiteGuardar;
	}
	
	public void addDireccion() {
		try {
			boolean guardar = true;
			
			//Validar obligatoriedad de campos
			if(validar(direccion)) {
				
				//Validar que no exista un registro duplicado
				for(Direccion dir : listaDirecciones) {
					if(dir.getNombre().equals(direccion.getNombre())) {
						guardar = false;						
					}
				}
				
				if(guardar) {
					getDireccionService().addDireccion(direccion);
					listaDirecciones = getDireccionService().getDirecciones(false);
					direccion = new Direccion();
					util.mostrarMensaje("Registro agregado con �xito."); 
				}else {
					util.mostrarError("Ya existe una Direcci�n con el mismo nombre");
				}
			}			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarError("Error guardando el registro.");
		} 
	}

	public void updateDireccion() {
		try {
			if(validar(selectedDireccion)) {
				getDireccionService().updateDireccion(selectedDireccion);
				listaDirecciones = getDireccionService().getDirecciones(false);
				selectedDireccion = new Direccion();
				util.mostrarMensaje("Registro actualizado con �xito.");
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarError("Error actualizando el registro.");
		}
	}
	
	public void deleteDireccion() {
		try {
			getDireccionService().deleteDireccion(selectedDireccion);
			listaDirecciones = getDireccionService().getDirecciones(false);
			util.mostrarMensaje("Registro eliminado con �xito.");  			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarError("Error eliminando el registro.");
		} 	
	}

	public IDireccionService getDireccionService() {
		return direccionService;
	}

	public void setDireccionService(IDireccionService direccionService) {
		this.direccionService = direccionService;
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Direccion getSelectedDireccion() {
		return selectedDireccion;
	}

	public void setSelectedDireccion(Direccion selectedDireccion) {
		this.selectedDireccion = selectedDireccion;
	}

	public List<Direccion> getListaDireccions() {
		return listaDirecciones;
	}

	public void setListaDireccions(List<Direccion> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}

 }