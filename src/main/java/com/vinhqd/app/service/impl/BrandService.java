package com.vinhqd.app.service.impl;

import com.vinhqd.app.component.ModelConverter;
import com.vinhqd.app.dto.BrandDTO;
import com.vinhqd.app.entity.BrandEntity;
import com.vinhqd.app.repository.BrandRepository;
import com.vinhqd.app.service.IBrandService;
import com.vinhqd.app.utils.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelConverter converter;

    @Autowired
    private VNCharacterUtils characterUtils;

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream()
                .map(item -> converter.toDTO(BrandDTO.class, item)).collect(Collectors.toList());
    }

    @Override
    public BrandDTO save(BrandDTO brandDTO) {
        BrandEntity entity = converter.toEntity(BrandEntity.class, brandDTO);
        if (brandDTO.getId() != null) {
            BrandEntity oldEntity = brandRepository.findById(brandDTO.getId()).get();
            entity = converter.toEntity(oldEntity, entity);
        }
        entity.setNameUnsigned(characterUtils.removeAccent(entity.getName()));
        return converter.toDTO(BrandDTO.class, brandRepository.save(entity));
    }

    @Override
    public BrandDTO findById(long id) {
        return converter.toDTO(BrandDTO.class, brandRepository.findById(id).get());
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(item -> brandRepository.deleteById(item));
    }
}
