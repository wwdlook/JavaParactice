package com.demo.practice.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public  class City  {
    private Long ID;

    private String Name;

    private String CountryCode;

    private String District;

    private Integer Population;

    public City(){}

    public City(String name, String countryCode, String district, Integer population) {
//        this.ID = ID;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }


}
