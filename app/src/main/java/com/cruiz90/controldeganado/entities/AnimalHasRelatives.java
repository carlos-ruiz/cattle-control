package com.cruiz90.controldeganado.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Carlos on 15/03/2017.
 */

@Entity
public class AnimalHasRelatives {
    @Id(autoincrement = true)
    private Long id;
    private Long animalParentId;
    private Long animalRelativeId;

    public AnimalHasRelatives(Long animalParentId, Long animalRelativeId) {
        this.animalParentId = animalParentId;
        this.animalRelativeId = animalRelativeId;
    }
    @Generated(hash = 1592942175)
    public AnimalHasRelatives(Long id, Long animalParentId, Long animalRelativeId) {
        this.id = id;
        this.animalParentId = animalParentId;
        this.animalRelativeId = animalRelativeId;
    }
    @Generated(hash = 1008990303)
    public AnimalHasRelatives() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAnimalParentId() {
        return this.animalParentId;
    }
    public void setAnimalParentId(Long animalParentId) {
        this.animalParentId = animalParentId;
    }
    public Long getAnimalRelativeId() {
        return this.animalRelativeId;
    }
    public void setAnimalRelativeId(Long animalRelativeId) {
        this.animalRelativeId = animalRelativeId;
    }
}
