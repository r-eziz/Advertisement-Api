
package com.eziz.service.impl;

import com.eziz.dto.DtoImage;
import com.eziz.model.Image;
import com.eziz.repository.ImageRepository;
import com.eziz.service.IImageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements IImageService{
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Value("${file.upload.dir}")
    private String uploadDir;
    
    @Override
    public DtoImage uploadImage(MultipartFile file) {
        
        System.out.println("UPLOAD DIR: " + uploadDir);
        String orjinalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID() + "_" + orjinalFileName;
        DtoImage dtoImage = new DtoImage();
        
        Path uploadPath = Path.of(uploadDir);
        
        if(!Files.exists(uploadPath)){
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException ex) {
                System.getLogger(ImageServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
        Path filePath = uploadPath.resolve(uniqueFileName);
        try {        
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.getLogger(ImageServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        Image image = new Image();
        
        image.setPath(filePath.toString());
        
        Image savedImage = imageRepository.save(image);
        
        BeanUtils.copyProperties(savedImage, dtoImage);
        
        return dtoImage;
        
    }

    @Override
    public Resource downloadImage(Integer id) {
        
        Image image = imageRepository.findById(id).get();
        
        Path path = Path.of(image.getPath());
        
        Resource resource = new FileSystemResource(path);
        
        return resource;
    }
    
}
