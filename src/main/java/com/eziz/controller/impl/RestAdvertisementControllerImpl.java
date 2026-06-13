
package com.eziz.controller.impl;

import com.eziz.controller.IRestAdvertisementController;
import com.eziz.dto.DtoAdvertisement;
import com.eziz.dto.DtoAdvertisementIU;
import com.eziz.service.IAdvertisementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/advertisement")
public class RestAdvertisementControllerImpl implements IRestAdvertisementController{
    
    @Autowired
    private IAdvertisementService advertisementService;
    
    @PostMapping("/upload")
    @Override
    public DtoAdvertisement uploadAdvertisement(@RequestBody DtoAdvertisementIU request) {
        
        return advertisementService.uploadAdvertisement(request);
        
    }
    
    @GetMapping("/dowload/{id}")
    @Override
    public DtoAdvertisementIU dowloadAdvertisement(@PathVariable(name = "id") Integer id) {
        
        return advertisementService.dowloadAdvertisement(id);
        
    }
    
    @GetMapping("/dowload/all")
    @Override
    public List<DtoAdvertisementIU> dowloadAllAdvertisementToPage(
            @RequestParam(name = "pageSize") Integer pageSize,
            @RequestParam(name = "pageNumber") Integer pageNumber) {
        
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        
        return advertisementService.dowloadAllAdvertisementToPage(pageable);
        
    }
    
}
