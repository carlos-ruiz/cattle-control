package com.cruiz90.controldeganado.util;

import com.cruiz90.controldeganado.entities.DaoMaster;
import com.cruiz90.controldeganado.entities.DaoSession;

import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by ISC. Carlos Ruiz on 27/05/2017.
 */

public class DBConnection {
    private static final String DB_NAME = "ganado-db";

    private static Database db;
    private static DaoSession daoSession;
    private static DBConnection instance = null;

    public synchronized static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public DaoSession getSession(){
        return daoSession;
    }

    private DBConnection() {
        if(App.DEBUG){
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(App.context, DB_NAME);
        db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public <T> long insert(T entity ){
        long id = daoSession.insert(entity);
        return id;
    }

    public <T> void insert(T entity, Class clazz, AsyncOperationListener asyncOperationListener){
        AsyncSession id = daoSession.startAsyncSession();
        id.setListener(asyncOperationListener);
        id.insertInTx(clazz, entity);
    }

    public <T> long insertOrReplace(T entity ){
        if(entity != null){
            long id = daoSession.insertOrReplace(entity);
            return id;
        } return 0;
    }

    public <T> void insertOrReplaceAll(List<T> entities ){
        for (T entity : entities)
            daoSession.insertOrReplace(entity);
    }

    public <T> void insertAll(List<T> entities ){
        for (T entity : entities)
            daoSession.insert(entity);
    }

    public <T, K> T load(Class<T> entityClass, K key){
        T result = daoSession.load(entityClass,key);
        return result;
    }

    public <T> List<T> loadAll(Class<T> entityClass){
        List<T> result = daoSession.loadAll(entityClass);
        return result;
    }

    public <T> void refresh(T entity){
        daoSession.refresh(entity);
    }

    public <T> void delete(T entity){
        if(entity != null)
            daoSession.delete(entity);
    }

    public <T> void deleteAll(Class<T> entityClass){
        daoSession.deleteAll(entityClass);
    }

    public <T> long count(Class<T> entityClass){
        return daoSession.getDao(entityClass).count();
    }

    public <T> QueryBuilder<T> queryBuilder(Class<T> entityClass){
        if(daoSession == null || entityClass == null)
            return null;
        return daoSession.queryBuilder(entityClass);

    }

    public void closeConnection(){
        if(db != null)
            db.close();
    }
}
