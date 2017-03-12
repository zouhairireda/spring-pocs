package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

	FileUpload findByFilename(String filename);
}
