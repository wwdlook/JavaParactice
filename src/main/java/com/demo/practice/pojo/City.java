package com.demo.practice.pojo;

import lombok.Data;

@Data
public  class City  {
    public Long ID;

    public String Name;

    public String CountryCode;

    public String District;

    public Integer Population;

    public City(){}

    public City(Long ID, String name, String countryCode, String district, Integer population) {
        this.ID = ID;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }
}
