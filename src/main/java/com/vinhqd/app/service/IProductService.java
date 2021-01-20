package com.vinhqd.app.service;

import com.vinhqd.app.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(long id);

    ProductDTO save(ProductDTO dto);

    void delete(long[] ids);

}
