
package com.eziz.controller;

import com.eziz.dto.DtoImage;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IRestImageController {
    
    public DtoImage uploadImage(MultipartFile file);
    
    public ResponseEntity<Resource> dowloadImage(Integer id);
}
