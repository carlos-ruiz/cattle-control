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
    private Long animalTypeId;
    @ToOne(joinProperty = "animalTypeId")
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

    private Long motherId;
    @ToOne(joinProperty = "motherId")
    private Animal mother;
    private Long fatherId;
    @ToOne(joinProperty = "fatherId")
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
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1746493452)
    private transient AnimalDao myDao;
    @Generated(hash = 93308694)
    private transient Long animalType__resolvedKey;
    @Generated(hash = 183549719)
    private transient Long mother__resolvedKey;
    @Generated(hash = 2100996716)
    private transient Long father__resolvedKey;

    public Animal(AnimalType animalType, String name, Float buyPrice, DateTime bithdate,
                  Float birthWeight, String color, Boolean isMale, DateTime weaningdate,
                  Float weaningWeight, DateTime solddate, Float soldWeight, Float soldPrice, Animal mother, Animal father) {
        this.animalType = animalType;
        this.animalTypeId = animalType.getAnimalTypeId();
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
        this.mother = mother;
        this.motherId = mother == null ? null : mother.getAnimalId();
        this.father = father;
        this.fatherId = father == null ? null : father.getAnimalId();
    }

    @Generated(hash = 954697352)
    public Animal(Long animalId, Long animalTypeId, String name, Float buyPrice, DateTime bithdate, Float birthWeight,
                  String color, Boolean isMale, DateTime weaningdate, Float weaningWeight, DateTime solddate, Float soldWeight,
                  Float soldPrice, Long motherId, Long fatherId) {
        this.animalId = animalId;
        this.animalTypeId = animalTypeId;
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
        this.motherId = motherId;
        this.fatherId = fatherId;
    }

    @Generated(hash = 308569294)
    public Animal() {
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(Long animalTypeId) {
        this.animalTypeId = animalTypeId;
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

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 594573345)
    public AnimalType getAnimalType() {
        Long __key = this.animalTypeId;
        if (animalType__resolvedKey == null || !animalType__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalTypeDao targetDao = daoSession.getAnimalTypeDao();
            AnimalType animalTypeNew = targetDao.load(__key);
            synchronized (this) {
                animalType = animalTypeNew;
                animalType__resolvedKey = __key;
            }
        }
        return animalType;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1705821842)
    public void setAnimalType(AnimalType animalType) {
        synchronized (this) {
            this.animalType = animalType;
            animalTypeId = animalType == null ? null : animalType.getAnimalTypeId();
            animalType__resolvedKey = animalTypeId;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 691857630)
    public Animal getMother() {
        Long __key = this.motherId;
        if (mother__resolvedKey == null || !mother__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalDao targetDao = daoSession.getAnimalDao();
            Animal motherNew = targetDao.load(__key);
            synchronized (this) {
                mother = motherNew;
                mother__resolvedKey = __key;
            }
        }
        return mother;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 282997677)
    public void setMother(Animal mother) {
        synchronized (this) {
            this.mother = mother;
            motherId = mother == null ? null : mother.getAnimalId();
            mother__resolvedKey = motherId;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1277689425)
    public Animal getFather() {
        Long __key = this.fatherId;
        if (father__resolvedKey == null || !father__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalDao targetDao = daoSession.getAnimalDao();
            Animal fatherNew = targetDao.load(__key);
            synchronized (this) {
                father = fatherNew;
                father__resolvedKey = __key;
            }
        }
        return father;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1223263980)
    public void setFather(Animal father) {
        synchronized (this) {
            this.father = father;
            fatherId = father == null ? null : father.getAnimalId();
            father__resolvedKey = fatherId;
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

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
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

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
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

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
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
