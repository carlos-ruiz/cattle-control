package com.cruiz90.controldeganado.entities;

import com.cruiz90.controldeganado.converters.DateTimeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.joda.time.DateTime;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Carlos on 15/03/2017.
 */

@Entity
public class AnimalHasDiseases {
    @Id(autoincrement = true)
    private Long id;
    private Long animalId;
    private Long diseaseId;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private DateTime diseaseDate;

    public AnimalHasDiseases(Long animalId, Long diseaseId) {
        this.animalId = animalId;
        this.diseaseId = diseaseId;
    }

    @Generated(hash = 1901730562)
    public AnimalHasDiseases(Long id, Long animalId, Long diseaseId,
            DateTime diseaseDate) {
        this.id = id;
        this.animalId = animalId;
        this.diseaseId = diseaseId;
        this.diseaseDate = diseaseDate;
    }

    @Generated(hash = 2010058988)
    public AnimalHasDiseases() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAnimalId() {
        return this.animalId;
    }
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
    public Long getDiseaseId() {
        return this.diseaseId;
    }
    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public DateTime getDiseaseDate() {
        return diseaseDate;
    }

    public void setDiseaseDate(DateTime diseaseDate) {
        this.diseaseDate = diseaseDate;
    }
}
