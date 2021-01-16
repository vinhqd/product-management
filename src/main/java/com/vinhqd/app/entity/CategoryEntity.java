package com.vinhqd.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

    private String name;

    @Column(name = "nameunsigned")
    private String nameUnsigned;
    private String code;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductEntity> products = new ArrayList<>();

}
