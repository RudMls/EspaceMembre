package fr.ut1.espacemembre.dao;

import fr.ut1.espacemembre.config.Database;

import java.sql.Connection;
import java.util.List;

/**
 * Cette classe implémente plusieurs méthodes abstraites qui permettront d'interagir avec la base de données.
 * @param <T> L'objet qui représente le modèle
 */
public abstract class DAO<T> {

    protected static Connection connection = Database.getConnection();

    /**
     *
     * @param objet L'objet que l'on veux créer
     * @return L'objet crée initialement
     */
    public abstract T create(T objet);

    /**
     *
     * @return Une list d'objet
     */
    public abstract List<T> findAll();

    /**
     *
     * @param id L'identifiant de l'objet en base de donnée
     * @return Un objet
     */
    public abstract T findById(int id);

    /**
     *
     * @param objet L'objet que l'on veut modifier
     */
    public abstract void update(T objet);

    /**
     *
     * @param id L'identifiant de l'obet que l'on veux supprimer
     */
    public abstract void deleteById(int id);

}

