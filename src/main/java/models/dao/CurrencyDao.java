package models.dao;

import models.Currensie;
import models.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CurrencyDao {

    public Currensie getByCode(String code) {
        String queryTemplate = "SELECT * FROM Currencies WHERE Code = ?";
        Currensie currency = new Currensie();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setString(1, code);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
               currency.setId(result.getInt("Id"));
               currency.setCode(result.getString("Code"));
               currency.setFullName(result.getString("FullName"));
               currency.setSign(result.getString("Sign"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }
}
