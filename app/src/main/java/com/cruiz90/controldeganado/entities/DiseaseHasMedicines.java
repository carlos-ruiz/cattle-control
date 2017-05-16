package com.cruiz90.controldeganado.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Carlos on 15/03/2017.
 */

@Entity
public class DiseaseHasMedicines {
    @Id(autoincrement = true)
    private Long id;
    private Long diseaseId;
    private Long medicineId;

    public DiseaseHasMedicines(Long diseaseId, Long medicineId) {
        this.diseaseId = diseaseId;
        this.medicineId = medicineId;
    }

    @Generated(hash = 1149326393)
    public DiseaseHasMedicines(Long id, Long diseaseId, Long medicineId) {
        this.id = id;
        this.diseaseId = diseaseId;
        this.medicineId = medicineId;
    }

    @Generated(hash = 1840820557)
    public DiseaseHasMedicines() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDiseaseId() {
        return this.diseaseId;
    }
    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }
    public Long getMedicineId() {
        return this.medicineId;
    }
    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
}
