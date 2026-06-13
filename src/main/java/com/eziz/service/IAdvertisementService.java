
package com.eziz.service;

import com.eziz.dto.DtoAdvertisement;
import com.eziz.dto.DtoAdvertisementIU;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IAdvertisementService {
    
    public DtoAdvertisement uploadAdvertisement(DtoAdvertisementIU dtoAdvertisementIU);
   
    public DtoAdvertisementIU dowloadAdvertisement(Integer id);
    
    public List<DtoAdvertisementIU> dowloadAllAdvertisementToPage(Pageable pageable);
    
}
