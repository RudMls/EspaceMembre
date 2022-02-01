package fr.ut1.espacemembre.dao;

import fr.ut1.espacemembre.config.Database;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

    protected static Connection connection = Database.getConnection();

    /**
     *
     * @param objet
     * @return
     */
    public abstract T create(T objet);

    /**
     *
     * @return
     */
    public abstract List<T> findAll();

    /**
     *
     * @param id
     * @return
     */
    public abstract T findById(int id);

    /**
     *
     * @param objet
     */
    public abstract void update(T objet);

    /**
     *
     * @param id
     */
    public abstract void deleteById(int id);

}

