package com.cruiz90.controldeganado.entities;

import android.support.annotation.Nullable;

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
    private Integer periodicityInMonths;

    public Vaccine(String name, Float dosePerKg, Boolean isPregnantAllowed, @Nullable Integer periodicityInMonths) {
        this.name = name;
        this.dosePerKg = dosePerKg;
        this.isPregnantAllowed = isPregnantAllowed;
        this.periodicityInMonths = periodicityInMonths;
    }

    @Generated(hash = 37933919)
    public Vaccine(Long vaccineId, String name, Float dosePerKg, Boolean isPregnantAllowed, Integer periodicityInMonths) {
        this.vaccineId = vaccineId;
        this.name = name;
        this.dosePerKg = dosePerKg;
        this.isPregnantAllowed = isPregnantAllowed;
        this.periodicityInMonths = periodicityInMonths;
    }

    @Generated(hash = 2125395277)
    public Vaccine() {
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

    public Boolean getIsPregnantAllowed() {
        return this.isPregnantAllowed;
    }

    public void setIsPregnantAllowed(Boolean isPregnantAllowed) {
        this.isPregnantAllowed = isPregnantAllowed;
    }

    public Integer getPeriodicityInMonths() {
        return periodicityInMonths;
    }

    public void setPeriodicityInMonths(Integer periodicityInMonths) {
        this.periodicityInMonths = periodicityInMonths;
    }

    @Override
    public String toString() {
        return name;
    }
}
