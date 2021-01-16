package com.vinhqd.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class ProductEntity extends BaseEntity {

    private String title;
    private String description;
    private double price;
    private String thumbnail;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CategoryEntity category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brandid")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BrandEntity brand;

}
