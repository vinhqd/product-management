package com.vinhqd.app.repository;

import com.vinhqd.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByUserName(String userName);

    UserEntity findOneByUserNameAndStatus(String userName, int status);

}
