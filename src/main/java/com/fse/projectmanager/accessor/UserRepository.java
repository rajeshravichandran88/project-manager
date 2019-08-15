package com.fse.projectmanager.accessor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projectmanager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
