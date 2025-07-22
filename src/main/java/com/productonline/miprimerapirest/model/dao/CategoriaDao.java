package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaDao extends CrudRepository<Categoria,Integer> {
}