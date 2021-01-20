package com.vinhqd.app.dto;

import lombok.Data;

@Data
public class ProductDTO extends BaseDTO{

    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private double price;
    private String brandName;
    private String brandCode;
    private String categoryName;
    private String CategoryCode;

}
