
package com.eziz.controller.impl;

import com.eziz.controller.IRestImageController;
import com.eziz.dto.DtoImage;
import com.eziz.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/images")
public class RestImageController implements IRestImageController{
    
    @Autowired
    private IImageService imageService;
    
    @PostMapping("/upload")
    public DtoImage uploadImage(@RequestParam("file") MultipartFile file){
        
        return imageService.uploadImage(file);
        
    }
    
    @GetMapping("/dowload/{id}")
    @Override
    public ResponseEntity<Resource> dowloadImage(@PathVariable(name = "id") Integer id) {
        
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.downloadImage(id));
        
    }
    
}
