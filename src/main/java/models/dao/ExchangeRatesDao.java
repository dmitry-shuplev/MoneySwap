package models.dao;

import models.DBUtils;
import models.ExchangeRates;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesDao {

    public List<ExchangeRates> getAll() {
        List<ExchangeRates> rates = new ArrayList<>();
        String queryTemplate = "SELECT * FROM ExchangeRates";

        try (Connection connection = DBUtils.getConnection(); PreparedStatement pstmt = connection.prepareStatement(queryTemplate);) {
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
                "JOIN Currencies AS BaseCurrencies ON BaseCurrencies.Id = ExchangeRates.BaseCurrencyId "
                + "JOIN Currencies AS TargetCurrencies ON TargetCurrencies.Id = ExchangeRates.TargetCurrencyId "
                + "WHERE BaseCurrencies.Code = ? AND TargetCurrencies.Code = ?";
        try (Connection connection = DBUtils.getConnection(); PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setString(1, base);
            pstmt.setString(2, target);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                rate = popularRate(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rate;
    }

    public void addToDb(String base, String target, String rate) {
        ExchangeRates exchangeRate = popularRate(base, target, rate);
        String queryTemplate = "INSERT INTO ExchangeRates (BaseCurrencyID, TargetCurrencyId, Rate) VALUES (?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setInt(1, exchangeRate.getBaseCurrency());
            pstmt.setInt(2, exchangeRate.getTargetCurency());
            pstmt.setBigDecimal(3, exchangeRate.getRate());
            pstmt.executeUpdate();
            System.out.println("Successfully added to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDb(String base, String target, String rate) {
        String queryTemplate = "UPDATE ExchangeRates SET Rate = ?" +
                " WHERE BaseCurrencyId = (SELECT Id FROM Currencies WHERE Code = ?)" +
                " AND TargetCurrencyId = (SELECT Id FROM Currencies WHERE Code = ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setBigDecimal(1, new BigDecimal(rate));
            pstmt.setString(2, base);
            pstmt.setString(3, target);
            int i = 10;
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private ExchangeRates popularRate(ResultSet result) throws SQLException {
        ExchangeRates rate = new ExchangeRates();
        rate.setId(result.getInt("Id"));
        rate.setBaseCurrency(result.getInt("BaseCurrencyId"));
        rate.setTargetCurency(result.getInt("TargetCurrencyId"));
        rate.setRate(result.getBigDecimal("Rate"));
        return rate;
    }

    private ExchangeRates popularRate(String baseCurrencyCode, String targetCurrencyCode, String rateValue) {
        ExchangeRates rate = new ExchangeRates();
        CurrencyDao currencyDao = new CurrencyDao();
        int baseCurrencyId = currencyDao.getId(baseCurrencyCode);
        int targetCurrencyId = currencyDao.getId(targetCurrencyCode);
        BigDecimal rateV = new BigDecimal(rateValue);
        rate.setBaseCurrency(baseCurrencyId);
        rate.setTargetCurency(targetCurrencyId);
        rate.setRate(rateV);
        return rate;
    }


}
