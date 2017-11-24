package com.larinego.entities;

import java.io.Serializable;

/**
 * Class DAO
 *
 * Created by yslabko on 07/02/2017.
 */
public interface DAO<T> {
    boolean save(T t);
    T get(Serializable id);
    boolean update(T t);
    boolean delete(Serializable id);
}
