
package com.eziz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "advertisement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    private String name;
    
    private BigDecimal price;
    
    private String description;
    
    @OneToMany
    @JoinTable(
            name = "advertisement_image",
            uniqueConstraints = @UniqueConstraint(
                columnNames = {"advertisement_id", "image_id"},
                name = "UC_adver_image"
            ),
            joinColumns = @JoinColumn(
                    name = "advertisement_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "image_id",
                    referencedColumnName = "id"
            )
    )
    private List<Image> image;
}
