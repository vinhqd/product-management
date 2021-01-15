package com.vinhqd.app.service;

import com.vinhqd.app.dto.BrandDTO;
import com.vinhqd.app.dto.CategoryDTO;

import java.util.List;

public interface IBrandService {

    List<BrandDTO> findAll();

    BrandDTO save(BrandDTO brandDTO);

    BrandDTO findById(long id);

    void delete(long[] ids);

}
