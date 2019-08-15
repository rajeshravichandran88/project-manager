package com.fse.projectmanager.accessor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projectmanager.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
