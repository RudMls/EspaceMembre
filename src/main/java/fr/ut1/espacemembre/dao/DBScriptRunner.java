package fr.ut1.espacemembre.dao;

import fr.ut1.espacemembre.config.Database;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

public class DBScriptRunner {

    public static void main(String[] args) {
        executeScript("file/create.sql");
    }

    public static void executeScript(String path) {
        try (Connection connection = Database.getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            try (Reader reader = new BufferedReader(new FileReader(path))) {
                scriptRunner.runScript(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
