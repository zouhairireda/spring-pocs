package com.example.controllers;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.model.FileUpload;
import com.example.services.FileUploadService;

@RestController
@CrossOrigin
public class FileController {

	@Autowired
	FileUploadService fileUploadService;

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity downloadFile(@RequestParam("filename") String filename) {

		FileUpload fileUpload = fileUploadService.findByFilename(filename);

		if (fileUpload == null) {
			return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "attachment; filename=" + fileUpload.getFilename());
		String primaryType, subType;
		try {
			primaryType = fileUpload.getMimeType().split("/")[0];
			subType = fileUpload.getMimeType().split("/")[1];
		} catch (IndexOutOfBoundsException | NullPointerException ex) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		headers.setContentType(new MediaType(primaryType, subType));

		return new ResponseEntity<>(fileUpload.getFile(), headers, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity uploadFile(MultipartHttpServletRequest request) {
		try {
			Iterator<String> itr = request.getFileNames();

			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				
				String csv = new String(bytes);
				System.out.println(csv);

				FileUpload newFile = new FileUpload(filename, bytes, mimeType);

				fileUploadService.uploadFile(newFile);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

}
