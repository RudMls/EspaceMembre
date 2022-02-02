package fr.ut1.espacemembre.dao;

import fr.ut1.espacemembre.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette permet d'intéragir avec la table user dans la base de donnée
 */
public class UserDao extends DAO<User> {

    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_BY_EMAIL = "SELECT 1 FROM user WHERE email = ?";
    private static final String SELECT_BY_EMAIL_PWD = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String CREATE = "INSERT INTO user (first_name, last_name, email, date_of_birth, password) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET first_name = ?, last_name = ?, email = ?, date_of_birth = ?, password = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM _nuser WHERE id = ?";

    /**
     *
     * @param email L'adresese email de l'utilisateur
     * @return Vrai si l'utilisateur existe, faux sinon
     */
    public boolean isEmailExist(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param email L'adresse email de l'utilisateur
     * @param password Le mot de passe de l'utilisateur
     * @return L'identifiant de l'utilsateur
     */
    public int loginByEmailPwd(String email, String password) {
        int userId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_PWD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                userId = resultSet.getInt("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFistName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, user.getDateofBirth());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) user.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("date_of_birth"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFistName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setDateofBirth(resultSet.getDate("date_of_birth"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  user;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getFistName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, user.getDateofBirth());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User user = new User(
            "Ruddy",
            "Monlouis",
            Date.valueOf(LocalDate.of(1997, Month.AUGUST, 5)),
            "r.monlouis@gmail.com",
            "pwd"
        );
        userDao.create(user);
    }

}
