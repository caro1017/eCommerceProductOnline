package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.DetalleFactura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DetalleFacturaDao extends CrudRepository<DetalleFactura, String> {
    //List<DetalleFactura> findByIdFactura(String idFactura);
    List<DetalleFactura> findByFactura_IdFactura(String idFactura);

    @Modifying
    @Transactional
    @Query("DELETE FROM DetalleFactura d WHERE d.factura.idFactura = :idFactura")
    void deleteByIdFactura(@Param("idFactura") String idFactura);
}


