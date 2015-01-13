package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChildRepository extends JpaRepository<Child, Long>, JpaSpecificationExecutor<Child> {

}
