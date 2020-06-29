package com.demo.practice.dao.secondary;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Voter {
    @Id
    @Column
    private Long id;

    private Integer vote_id;
    private String name;
}
