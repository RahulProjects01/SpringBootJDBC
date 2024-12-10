package org.example.SpringBootJDBC.dao;

import org.example.SpringBootJDBC.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertUser(User user) {
        boolean status = false;
        try {
            String insert_sql_query = "INSERT INTO users (name, email, gender, city) VALUES (?, ?, ?, ?)";
            int count = jdbcTemplate.update(insert_sql_query, user.getName(), user.getEmail(), user.getGender(), user.getCity());
            status = count > 0;
        } catch (Exception e) {
            System.out.println("Error occurred while inserting user.");
            e.printStackTrace();
        }
        return status;
    }

    public boolean updateUser(User user) {
        boolean status = false;
        try {
            String update_sql_query = "UPDATE users SET name=?, email=?, gender=?, city=? WHERE email=?";
            int count = jdbcTemplate.update(update_sql_query, user.getName(), user.getEmail(), user.getGender(), user.getCity(), user.getEmail());
            status = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean deleteUser(String email) {
        boolean status = false;
        try {
            String delete_sql_query = "DELETE FROM users WHERE email=?";
            int count = jdbcTemplate.update(delete_sql_query, email);
            status = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public User getUserByEmail(String email) {
        String select_sql_query = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(select_sql_query, new UserRowMapper(), email);
        } catch (Exception e) {
            System.out.println("User not found: " + e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        String select_sql_query = "SELECT * FROM users";
        return jdbcTemplate.query(select_sql_query, new UserRowMapper());
    }

    public static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setCity(rs.getString("city"));
            return user;
        }
    }
}
