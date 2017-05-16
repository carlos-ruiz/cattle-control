package com.cruiz90.controldeganado.entities;

import com.cruiz90.controldeganado.converters.DateTimeConverter;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Carlos on 15/05/2017.
 */

@Entity
public class Animal {

    @Id(autoincrement = true)
    private Long animalId;

    @ToOne
    private AnimalType animalType;
    private String name;
    private Float buyPrice;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private DateTime bithdate;
    private Float birthWeight;
    private String color;
    private Boolean isMale;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private DateTime weaningdate;
    private Float weaningWeight;
    @Convert(converter = DateTimeConverter.class, columnType = Long.class)
    private DateTime solddate;
    private Float soldWeight;
    private Float soldPrice;

    @ToOne
    private Animal mother;
    @ToOne
    private Animal father;
    @ToMany
    @JoinEntity(
            entity = AnimalHasRelatives.class,
            sourceProperty = "animalParentId",
            targetProperty = "animalRelativeId"
    )
    private List<Animal> relatives = null;
    @ToMany
    @JoinEntity(
            entity = AnimalHasDiseases.class,
            sourceProperty = "animalId",
            targetProperty = "diseaseId"
    )
    private List<Disease> diseases = null;
    @ToMany
    @JoinEntity(
            entity = AnimalHasVaccines.class,
            sourceProperty = "animalId",
            targetProperty = "vaccineId"
    )
    private List<Vaccine> vaccines = null;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1746493452)
    private transient AnimalDao myDao;

    @Generated(hash = 635499102)
    public Animal(Long animalId, String name, Float buyPrice, DateTime bithdate,
            Float birthWeight, String color, Boolean isMale, DateTime weaningdate,
            Float weaningWeight, DateTime solddate, Float soldWeight,
            Float soldPrice) {
        this.animalId = animalId;
        this.name = name;
        this.buyPrice = buyPrice;
        this.bithdate = bithdate;
        this.birthWeight = birthWeight;
        this.color = color;
        this.isMale = isMale;
        this.weaningdate = weaningdate;
        this.weaningWeight = weaningWeight;
        this.solddate = solddate;
        this.soldWeight = soldWeight;
        this.soldPrice = soldPrice;
    }

    @Generated(hash = 308569294)
    public Animal() {
    }

    @Generated(hash = 573062317)
    private transient boolean animalType__refreshed;

    @Generated(hash = 1708982261)
    private transient boolean mother__refreshed;

    @Generated(hash = 1330230372)
    private transient boolean father__refreshed;

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getBithdate() {
        return bithdate;
    }

    public void setBithdate(DateTime bithdate) {
        this.bithdate = bithdate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(Float birthWeight) {
        this.birthWeight = birthWeight;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public Boolean getIsMale() {
        return this.isMale;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    public DateTime getWeaningdate() {
        return weaningdate;
    }

    public void setWeaningdate(DateTime weaningdate) {
        this.weaningdate = weaningdate;
    }

    public Float getWeaningWeight() {
        return weaningWeight;
    }

    public void setWeaningWeight(Float weaningWeight) {
        this.weaningWeight = weaningWeight;
    }

    public DateTime getSolddate() {
        return solddate;
    }

    public void setSolddate(DateTime solddate) {
        this.solddate = solddate;
    }

    public Float getSoldWeight() {
        return soldWeight;
    }

    public void setSoldWeight(Float soldWeight) {
        this.soldWeight = soldWeight;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(Float soldPrice) {
        this.soldPrice = soldPrice;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1036493918)
    public AnimalType getAnimalType() {
        if (animalType != null || !animalType__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalTypeDao targetDao = daoSession.getAnimalTypeDao();
            targetDao.refresh(animalType);
            animalType__refreshed = true;
        }
        return animalType;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 687740154)
    public AnimalType peakAnimalType() {
        return animalType;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 498491592)
    public void setAnimalType(AnimalType animalType) {
        synchronized (this) {
            this.animalType = animalType;
            animalType__refreshed = true;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1659181247)
    public Animal getMother() {
        if (mother != null || !mother__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalDao targetDao = daoSession.getAnimalDao();
            targetDao.refresh(mother);
            mother__refreshed = true;
        }
        return mother;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 1992951022)
    public Animal peakMother() {
        return mother;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1423114989)
    public void setMother(Animal mother) {
        synchronized (this) {
            this.mother = mother;
            mother__refreshed = true;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 609622899)
    public Animal getFather() {
        if (father != null || !father__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalDao targetDao = daoSession.getAnimalDao();
            targetDao.refresh(father);
            father__refreshed = true;
        }
        return father;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 1816497986)
    public Animal peakFather() {
        return father;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2126704041)
    public void setFather(Animal father) {
        synchronized (this) {
            this.father = father;
            father__refreshed = true;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 966693969)
    public List<Animal> getRelatives() {
        if (relatives == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalDao targetDao = daoSession.getAnimalDao();
            List<Animal> relativesNew = targetDao._queryAnimal_Relatives(animalId);
            synchronized (this) {
                if (relatives == null) {
                    relatives = relativesNew;
                }
            }
        }
        return relatives;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 794785943)
    public synchronized void resetRelatives() {
        relatives = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1718895351)
    public List<Disease> getDiseases() {
        if (diseases == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DiseaseDao targetDao = daoSession.getDiseaseDao();
            List<Disease> diseasesNew = targetDao._queryAnimal_Diseases(animalId);
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
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1984190280)
    public List<Vaccine> getVaccines() {
        if (vaccines == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VaccineDao targetDao = daoSession.getVaccineDao();
            List<Vaccine> vaccinesNew = targetDao._queryAnimal_Vaccines(animalId);
            synchronized (this) {
                if (vaccines == null) {
                    vaccines = vaccinesNew;
                }
            }
        }
        return vaccines;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 179217238)
    public synchronized void resetVaccines() {
        vaccines = null;
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
    @Generated(hash = 1486695615)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAnimalDao() : null;
    }
}
