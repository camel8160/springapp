package com.marketingpersonal.model.dao;

import java.util.List;

import com.marketingpersonal.model.entity.CentroCosto;
import com.marketingpersonal.model.entity.CentroCostoPorCuenta;


public interface ICentroCostoDAO {
	
	void addCentroCosto(CentroCosto entity);

	void updateCentroCosto(CentroCosto entity);
	
	void deleteCentroCosto(CentroCosto entity);
	
	CentroCosto getCentroCostoById(int id);

	List<CentroCosto> getCentroCostos(boolean activo);
	
	//Centro costo por cuenta
	void addCentroCostoPorCuenta(CentroCostoPorCuenta entity);

	void updateCentroCostoPorCuenta(CentroCostoPorCuenta entity);
	
	void deleteCentroCostoPorCuenta(CentroCostoPorCuenta entity);
	
	CentroCostoPorCuenta getCentroCostoPorCuentaById(int id);

	List<CentroCostoPorCuenta> getCentroCostoPorCuentas();

}
