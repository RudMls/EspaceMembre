package fr.ut1.espacemembre.dao;

import fr.ut1.espacemembre.config.Database;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

    protected static Connection connection = Database.getConnection();
    public abstract T create(T objet);
    public abstract List<T> findAll();
    public abstract T findById(int id);
    public abstract void update(T objet);
    public abstract void deleteById(int id);

}

