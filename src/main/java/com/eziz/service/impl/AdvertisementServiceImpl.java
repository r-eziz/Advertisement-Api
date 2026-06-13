
package com.eziz.service.impl;

import com.eziz.dto.DtoAdvertisement;
import com.eziz.dto.DtoAdvertisementIU;
import com.eziz.model.Advertisement;
import com.eziz.model.Image;
import com.eziz.repository.AdvertisementRepository;
import com.eziz.repository.ImageRepository;
import com.eziz.service.IAdvertisementService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements IAdvertisementService{
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public DtoAdvertisement uploadAdvertisement(DtoAdvertisementIU dtoAdvertisementIU) {
        
        Advertisement advertisement = new Advertisement();
        DtoAdvertisement dtoAdvertisement = new DtoAdvertisement();
        
        BeanUtils.copyProperties(dtoAdvertisementIU, advertisement);
        
        List<Image> imageList = new ArrayList<>();
        
        for (Integer i : dtoAdvertisementIU.getIdImages()){
            imageList.add(imageRepository.findById(i).get());
        }
        advertisement.setImage(imageList);
        
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);;
        
        BeanUtils.copyProperties(savedAdvertisement, dtoAdvertisement);
        
        return dtoAdvertisement;
        
    }

    @Override
    public DtoAdvertisementIU dowloadAdvertisement(Integer id) {
        
        Advertisement adver = advertisementRepository.findById(id).get();
        DtoAdvertisementIU adverIU = new DtoAdvertisementIU();
        
        BeanUtils.copyProperties(adver, adverIU);
        
        List<Integer> imageIds = new ArrayList<>();
        
        for(Image i : adver.getImage()){
            imageIds.add(i.getId());
        }
        adverIU.setIdImages(imageIds);
        
        return adverIU;
        
    }

    @Override
    public List<DtoAdvertisementIU> dowloadAllAdvertisementToPage(Pageable pageable) {
        
        Page<Advertisement> adverPage = advertisementRepository.findAll(pageable);
        
        
        List<DtoAdvertisementIU> dtoAdvertisementIUList = new ArrayList<>();
        List<Integer> imagesIds = new ArrayList<>();
        
        for(Advertisement adver : adverPage){
            
            DtoAdvertisementIU dtoAdvertisementIU = new DtoAdvertisementIU();
            
            BeanUtils.copyProperties(adver, dtoAdvertisementIU);
            
            for(Image image : adver.getImage()){
                
                imagesIds.add(image.getId());
                
            }
            dtoAdvertisementIU.setIdImages(imagesIds);
            
            dtoAdvertisementIUList.add(dtoAdvertisementIU);
            
        }
        
        
        
        return dtoAdvertisementIUList;
    }

}
