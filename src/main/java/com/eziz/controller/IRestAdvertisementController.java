
package com.eziz.controller;

import com.eziz.dto.DtoAdvertisement;
import com.eziz.dto.DtoAdvertisementIU;
import java.util.List;

public interface IRestAdvertisementController {
    
    public DtoAdvertisement uploadAdvertisement(DtoAdvertisementIU dtoAdvertisementIU);
    
    public DtoAdvertisementIU dowloadAdvertisement(Integer id);
    
    public List<DtoAdvertisementIU> dowloadAllAdvertisementToPage(Integer pageSize, Integer pageNumber);
}
