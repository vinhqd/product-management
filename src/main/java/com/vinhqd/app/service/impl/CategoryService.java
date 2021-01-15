package com.vinhqd.app.service.impl;

import com.vinhqd.app.component.ModelConverter;
import com.vinhqd.app.dto.CategoryDTO;
import com.vinhqd.app.entity.CategoryEntity;
import com.vinhqd.app.repository.CategoryRepository;
import com.vinhqd.app.service.ICategoryService;
import com.vinhqd.app.utils.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelConverter converter;

    @Autowired
    private VNCharacterUtils characterUtils;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(item -> converter.toDTO(CategoryDTO.class, item)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(long id) {
        return converter.toDTO(CategoryDTO.class, categoryRepository.findById(id).get());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity entity = converter.toEntity(CategoryEntity.class, categoryDTO);
        if (categoryDTO.getId() != null) {
            CategoryEntity oldEntity = categoryRepository.findById(categoryDTO.getId()).get();
            entity = converter.toEntity(oldEntity, entity);
        }
        entity.setNameUnsigned(characterUtils.removeAccent(entity.getName()).toLowerCase());
        return converter.toDTO(CategoryDTO.class, categoryRepository.save(entity));
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(item -> categoryRepository.deleteById(item));
    }
}
