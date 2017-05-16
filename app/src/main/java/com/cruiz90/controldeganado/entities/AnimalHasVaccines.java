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
public class AnimalHasVaccines {
    @Id(autoincrement = true)
    private Long id;
    private Long animalId;
    private Long vaccineId;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private DateTime vaccineDate;

    public AnimalHasVaccines(Long animalId, Long vaccineId) {
        this.animalId = animalId;
        this.vaccineId = vaccineId;
    }

    @Generated(hash = 1178743875)
    public AnimalHasVaccines(Long id, Long animalId, Long vaccineId,
            DateTime vaccineDate) {
        this.id = id;
        this.animalId = animalId;
        this.vaccineId = vaccineId;
        this.vaccineDate = vaccineDate;
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

    public DateTime getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(DateTime vaccineDate) {
        this.vaccineDate = vaccineDate;
    }
}
