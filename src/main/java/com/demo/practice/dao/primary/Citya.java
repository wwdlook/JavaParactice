package com.demo.practice.dao.primary;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Citya {
    @Id
    @Column
    private Long  id;

    @Column
    private String name;


    public Citya(){}

    public Citya(Long id, String name) {
        this.id = id;
        this.name = name;

    }

}
