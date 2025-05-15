package models.repository;

import models.Database;

import java.sql.Connection;
import java.util.List;

public abstract class CRUDRepository<T> {
    protected static final Connection connection = Database.getConnection();

    public abstract void create(T t);
    public abstract void update(T t);
    public abstract void delete(Integer id);
    public abstract List<T> list();
}