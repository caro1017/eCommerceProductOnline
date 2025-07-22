package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.model.dao.CategoriaDao;
import com.productonline.miprimerapirest.service.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaImpl implements ICategoria {

    @Autowired
    private CategoriaDao categoriaDao;

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Integer idCategoria) {
        return categoriaDao.existsById(idCategoria);
    }
}
