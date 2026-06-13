
package com.eziz.service;

import com.eziz.dto.DtoImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    
    public DtoImage uploadImage(MultipartFile file);
    
    public Resource downloadImage(Integer id);
    
}
