package com.example.testovoe.repository;


import com.example.testovoe.model.entity.FileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfoEntity, Long> {
    FileInfoEntity findByName(String name);
}
