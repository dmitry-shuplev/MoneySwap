package models.dao;

import models.Currensies;
import models.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CurrencyDao {

    public Currensies getByCode(String code) {
        String queryTemplate = "SELECT * FROM Currencies WHERE Code = ?";
        Currensies currency = new Currensies();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate);) {
            pstmt.setString(1, code);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
               currency = populateCurrency(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }

    public List<Currensies> getAll() {
        String queryTemplate = "SELECT * FROM Currencies";
        List<Currensies> currensies = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate);
             ResultSet result = pstmt.executeQuery()) {
            while (result.next()) {
                currensies.add(populateCurrency(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
int i =10;
        return currensies;
    }

    private Currensies populateCurrency(ResultSet result) throws SQLException{
        Currensies currency = new Currensies();
        currency.setId(result.getInt("Id"));
        currency.setCode(result.getString("Code"));
        currency.setFullName(result.getString("FullName"));
        currency.setSign(result.getString("Sign"));
        return currency;
    }
}



