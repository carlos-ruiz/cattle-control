package com.cruiz90.controldeganado.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Carlos on 15/05/2017.
 */

@Entity
public class Vaccine {
    @Id(autoincrement = true)
    private Long vaccineId;

    private String name;
    private Float dosePerKg;
    private Boolean isPregnantAllowed;

    @Generated(hash = 358366363)
    public Vaccine(Long vaccineId, String name, Float dosePerKg,
            Boolean isPregnantAllowed) {
        this.vaccineId = vaccineId;
        this.name = name;
        this.dosePerKg = dosePerKg;
        this.isPregnantAllowed = isPregnantAllowed;
    }

    @Generated(hash = 2125395277)
    public Vaccine() {
    }

    public Vaccine(String name, Float dosePerKg, Boolean isPregnantAllowed) {
        this.name = name;
        this.dosePerKg = dosePerKg;
        this.isPregnantAllowed = isPregnantAllowed;
    }

    public Long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDosePerKg() {
        return dosePerKg;
    }

    public void setDosePerKg(Float dosePerKg) {
        this.dosePerKg = dosePerKg;
    }

    public Boolean getPregnantAllowed() {
        return isPregnantAllowed;
    }

    public void setPregnantAllowed(Boolean pregnantAllowed) {
        isPregnantAllowed = pregnantAllowed;
    }

    public Boolean getIsPregnantAllowed() {
        return this.isPregnantAllowed;
    }

    public void setIsPregnantAllowed(Boolean isPregnantAllowed) {
        this.isPregnantAllowed = isPregnantAllowed;
    }

    @Override
    public String toString() {
        return name;
    }
}
