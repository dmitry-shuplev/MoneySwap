package models.dao;

import models.DBUtils;
import models.ExchangeRates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExchangeRatesDAO {

    public List<ExchangeRates> getAll() {
        List<ExchangeRates> rates = new ArrayList<>();
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
        return rates;
    }

    public ExchangeRates getExchangeRate(String base, String target) {
        ExchangeRates rate = new ExchangeRates();
        String queryTemplate = queryTemplate = "SELECT ExchangeRates.* FROM ExchangeRates " +
                "JOIN Currencies AS BaseCurrencies ON BaseCurrencies.Id = ExchangeRates.BaseCurrencyId " +
                "JOIN Currencies AS TargetCurrencies ON TargetCurrencies.Id = ExchangeRates.TargetCurrencyId " +
                "WHERE BaseCurrencies.Code = ? AND TargetCurrencies.Code = ?";
        try (Connection connection = DBUtils.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setString(1,base);
            pstmt.setString(2, target);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                rate = popularRate(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rate;
    }

    private ExchangeRates popularRate(ResultSet result) throws SQLException {
        ExchangeRates rate = new ExchangeRates();
        rate.setId(result.getInt("Id"));
        rate.setBaseCurrency(result.getInt("BaseCurrencyId"));
        rate.setTargetCurency(result.getInt("TargetCurrencyId"));
        rate.setRate(result.getBigDecimal("Rate"));
        return rate;
    }
}
