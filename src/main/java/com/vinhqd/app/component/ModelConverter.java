package com.vinhqd.app.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter {

    @Autowired
    private ModelMapper mapper;

    public <T, K> T toDTO(Class<T> tClass, K entity) {
        T t = mapper.map(entity, tClass);
        return t;
    }

    public <T, K> T toEntity(Class<T> tClass, K dto) {
        T t = mapper.map(dto, tClass);
        return t;
    }

    public <T> T toEntity(T oldEntity, T newEntity) {
        mapper.map(newEntity, oldEntity);
        return oldEntity;
    }

}
