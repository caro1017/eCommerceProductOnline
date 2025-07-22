package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.DetalleFactura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends CrudRepository<DetalleFactura, String> {
}
