package com.cruiz90.controldeganado.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Carlos on 15/05/2017.
 */

@Entity
public class AnimalType {
    @Id(autoincrement = true)
    private Long animalTypeId;

    private String name;

    @Generated(hash = 670548363)
    public AnimalType(Long animalTypeId, String name) {
        this.animalTypeId = animalTypeId;
        this.name = name;
    }

    @Generated(hash = 1921903429)
    public AnimalType() {
    }

    public Long getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(Long animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
