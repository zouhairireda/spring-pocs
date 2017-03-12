package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.FileUpload;
import com.example.repo.FileUploadRepository;

@Service
public class FileUploadService {

	@Autowired
	FileUploadRepository fileUploadRepository;

	public FileUpload findByFilename(String filename) {
		return fileUploadRepository.findByFilename(filename);
	}

	public void uploadFile(FileUpload doc) {
		fileUploadRepository.saveAndFlush(doc);
	}
}
