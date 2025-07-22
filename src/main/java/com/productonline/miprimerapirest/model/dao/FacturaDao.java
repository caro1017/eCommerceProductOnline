package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface FacturaDao extends CrudRepository<Factura,String> {

}
