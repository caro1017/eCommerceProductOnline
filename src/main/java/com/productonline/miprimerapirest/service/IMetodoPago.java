package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.entity.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetodoPago extends JpaRepository<MetodoPago, Integer> {

}
