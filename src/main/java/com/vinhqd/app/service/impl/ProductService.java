package com.vinhqd.app.service.impl;

import com.vinhqd.app.component.ModelConverter;
import com.vinhqd.app.dto.ProductDTO;
import com.vinhqd.app.entity.ProductEntity;
import com.vinhqd.app.repository.BrandRepository;
import com.vinhqd.app.repository.CategoryRepository;
import com.vinhqd.app.repository.ProductRepository;
import com.vinhqd.app.service.IProductService;
import com.vinhqd.app.utils.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VNCharacterUtils characterUtils;

    @Autowired
    private ModelConverter converter;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(item -> converter.toDTO(ProductDTO.class, item)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(long id) {
        return converter.toDTO(ProductDTO.class, productRepository.findById(id).get());
    }

    @Override
    public ProductDTO save(ProductDTO dto) {
        ProductEntity entity = converter.toEntity(ProductEntity.class, dto);
        if (dto.getId() != null) {
            ProductEntity oldEntity = productRepository.findById(dto.getId()).get();
            entity = converter.toEntity(oldEntity, entity);
        }
        if (dto.getThumbnail().equals("") || dto.getThumbnail() != null) {

        }
        entity.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
        entity.setBrand(brandRepository.findOneByCode(dto.getBrandCode()));
        entity.setTitleUnsigned(characterUtils.removeAccent(dto.getTitle()).toLowerCase());
        return converter.toDTO(ProductDTO.class, productRepository.save(entity));
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(id -> productRepository.deleteById(id));
    }
}
