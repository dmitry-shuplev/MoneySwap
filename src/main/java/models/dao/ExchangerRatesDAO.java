package models.dao;

import models.DBUtils;
import models.ExchangeRates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ExchangerRatesDAO {

    public List<ExchangeRates> getAll() {
        List<ExchangeRates> rates = new LinkedList<>();
        String queryTemplate = "SELECT * FROM ExchangeRates";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate);
        ) {
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                rates.add(popularRate(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        return rates;
    }

    private ExchangeRates popularRate(ResultSet result) throws SQLException {
        ExchangeRates rate = new ExchangeRates();
        rate.setId(result.getInt("Id"));
        rate.setBaseCurrency(result.getInt("BaseCurrencyId"));
        rate.setTargetCurency(result.getInt("BaseCurrencyId"));
        rate.setRate(result.getBigDecimal("Rate"));

        return rate;
    }
}
