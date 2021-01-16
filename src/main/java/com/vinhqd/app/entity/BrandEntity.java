package com.vinhqd.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity extends BaseEntity {

    private String name;

    @Column(name = "nameunsigned")
    private String nameUnsigned;
    private String code;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductEntity> products = new ArrayList<>();

}
