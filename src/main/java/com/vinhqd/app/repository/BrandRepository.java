package com.vinhqd.app.repository;

import com.vinhqd.app.entity.BrandEntity;
import com.vinhqd.app.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findOneByCode(String code);

}
