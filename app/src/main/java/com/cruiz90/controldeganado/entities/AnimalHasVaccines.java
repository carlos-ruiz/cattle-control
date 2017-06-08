package com.cruiz90.controldeganado.entities;

import com.cruiz90.controldeganado.converters.DateTimeConverter;
import com.cruiz90.controldeganado.util.DBConnection;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Carlos on 15/03/2017.
 */

@Entity
public class AnimalHasVaccines {
    @Id(autoincrement = true)
    private Long id;
    private Long animalId;
    private Long vaccineId;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private Date vaccineDate;

    private Long vaccineDateInMilis;

    public AnimalHasVaccines(Long animalId, Long vaccineId) {
        this.animalId = animalId;
        this.vaccineId = vaccineId;
    }

    public AnimalHasVaccines(Long animalId, Long vaccineId, Date vaccineDate) {
        this.animalId = animalId;
        this.vaccineId = vaccineId;
        this.vaccineDate = vaccineDate;
        this.vaccineDateInMilis = vaccineDate.getTime();
    }

    @Generated(hash = 1545424960)
    public AnimalHasVaccines(Long id, Long animalId, Long vaccineId,
            Date vaccineDate, Long vaccineDateInMilis) {
        this.id = id;
        this.animalId = animalId;
        this.vaccineId = vaccineId;
        this.vaccineDate = vaccineDate;
        this.vaccineDateInMilis = vaccineDateInMilis;
    }

    @Generated(hash = 987237036)
    public AnimalHasVaccines() {
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

    public Long getVaccineId() {
        return this.vaccineId;
    }

    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }

    public Date getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(Date vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public Long getVaccineDateInMilis() {
        return vaccineDateInMilis;
    }

    public void setVaccineDateInMilis(Long vaccineDateInMilis) {
        this.vaccineDateInMilis = vaccineDateInMilis;
    }

    public Animal getAnimal() {
        return DBConnection.getInstance().load(Animal.class, animalId);
    }

    public Vaccine getVaccine() {
        return DBConnection.getInstance().load(Vaccine.class, vaccineId);
    }

    @Override
    public String toString() {
        return getAnimal().getName();
    }
}
