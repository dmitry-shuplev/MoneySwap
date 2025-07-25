package models.dao;

import models.Currencies;
import models.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public Currencies getById(int currencyId) {
        String queryTemplate = "SELECT * FROM Currencies WHERE Id = ?";
        Currencies currency = new Currencies();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setInt(1, currencyId);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                currency = populateCurrency(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currency;
    }

    public Currencies getByCode(String code) {
        String queryTemplate = "SELECT * FROM Currencies WHERE Code = ?";
        Currencies currency = new Currencies();
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

    public int getId(String code) {
        Currencies currency = getByCode(code);
        return currency.getId();

    }

    public void addToDb(Currencies c) {
        String queryTemplate = "INSERT INTO Currencies (Code, FullName, Sign) VALUES (?, ?, ?)";
        try (Connection conneciton = DBUtils.getConnection();
             PreparedStatement pstmt = conneciton.prepareStatement(queryTemplate);) {
            pstmt.setString(1, c.getCode());
            pstmt.setString(2, c.getFullName());
            pstmt.setString(3, c.getSign());
            int i = 0;
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Currencies> getAll() {
        String queryTemplate = "SELECT * FROM Currencies";
        List<Currencies> currensies = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate);
             ResultSet result = pstmt.executeQuery()) {
            while (result.next()) {
                currensies.add(populateCurrency(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currensies;
    }

    private Currencies populateCurrency(ResultSet result) throws SQLException {
        Currencies currency = new Currencies();
        currency.setId(result.getInt("Id"));
        currency.setCode(result.getString("Code"));
        currency.setFullName(result.getString("FullName"));
        currency.setSign(result.getString("Sign"));
        return currency;
    }
}



