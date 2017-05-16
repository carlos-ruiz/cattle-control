package com.cruiz90.controldeganado.entities;

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
public class Disease {
    @Id(autoincrement = true)
    private Long diseaseId;
    private String name;
    private String description;
    @ToMany
    @JoinEntity(
            entity = DiseaseHasMedicines.class,
            sourceProperty = "diseaseId",
            targetProperty = "medicineId"
    )
    private List<Medicine> medicines = null;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1041835162)
    private transient DiseaseDao myDao;

    @Generated(hash = 1862522694)
    public Disease(Long diseaseId, String name, String description) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.description = description;
    }

    @Generated(hash = 1596955631)
    public Disease() {
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 702933541)
    public List<Medicine> getMedicines() {
        if (medicines == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MedicineDao targetDao = daoSession.getMedicineDao();
            List<Medicine> medicinesNew = targetDao
                    ._queryDisease_Medicines(diseaseId);
            synchronized (this) {
                if (medicines == null) {
                    medicines = medicinesNew;
                }
            }
        }
        return medicines;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1436134418)
    public synchronized void resetMedicines() {
        medicines = null;
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
    @Generated(hash = 109044434)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDiseaseDao() : null;
    }
}
