
package com.eziz.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAdvertisementIU {
    
    private Integer id; 
    
    private String name;
    
    private BigDecimal price;
    
    private String description;
    
    private List<Integer> idImages;
    
}
