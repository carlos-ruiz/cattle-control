package com.cruiz90.controldeganado.entities;

import com.cruiz90.controldeganado.converters.ListConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Carlos on 15/05/2017.
 */

@Entity
public class Medicine {
    @Id(autoincrement = true)
    private Long medicineId;

    private String name;
    @Convert(converter = ListConverter.class, columnType = String.class)
    private List<String> composition;
    private Float dosePerKg;
    private Boolean isPregnantAllowed;

    @ToMany
    @JoinEntity(
            entity = DiseaseHasMedicines.class,
            sourceProperty = "medicineId",
            targetProperty = "diseaseId"
    )
    private List<Disease> diseases = null;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 64903361)
    private transient MedicineDao myDao;

    @Generated(hash = 1532147725)
    public Medicine(Long medicineId, String name, List<String> composition,
            Float dosePerKg, Boolean isPregnantAllowed) {
        this.medicineId = medicineId;
        this.name = name;
        this.composition = composition;
        this.dosePerKg = dosePerKg;
        this.isPregnantAllowed = isPregnantAllowed;
    }

    @Generated(hash = 1065091254)
    public Medicine() {
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComposition() {
        return composition;
    }

    public void setComposition(List<String> composition) {
        this.composition = composition;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1578465764)
    public List<Disease> getDiseases() {
        if (diseases == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DiseaseDao targetDao = daoSession.getDiseaseDao();
            List<Disease> diseasesNew = targetDao
                    ._queryMedicine_Diseases(medicineId);
            synchronized (this) {
                if (diseases == null) {
                    diseases = diseasesNew;
                }
            }
        }
        return diseases;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 456762677)
    public synchronized void resetDiseases() {
        diseases = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 734240498)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMedicineDao() : null;
    }
}
