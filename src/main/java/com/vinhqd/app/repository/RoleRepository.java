package com.vinhqd.app.repository;

import com.vinhqd.app.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findOneByCode(String code);

}
