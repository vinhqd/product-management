package com.vinhqd.app.service;

import com.vinhqd.app.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO findById(long id);

    void delete(long[] ids);

}
