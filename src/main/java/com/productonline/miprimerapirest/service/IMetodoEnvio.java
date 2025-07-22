package com.productonline.miprimerapirest.service;

import com.productonline.miprimerapirest.model.entity.MetodoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetodoEnvio extends JpaRepository<MetodoEnvio, Integer> {

}
