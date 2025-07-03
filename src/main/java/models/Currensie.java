package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Currensie {
    private static Connection connection = DBUtils.getConnection();
    private int id;
    private String code;
    private String fullName;
    private String sign;

    public Currensie(String c)
    {
        this.getByCode(c);
    }

    public Currensie getByCode(String code) {
        String queryTemplate = "SELECT * FROM Currencies WHERE Code = ?";
        //stub
        if(connection == null){
            System.out.println("CONNECTION IS LOSE");
            return null; }
        try(PreparedStatement pstmt = connection.prepareStatement(queryTemplate)) {
            pstmt.setString(1, code);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                this.id = result.getInt("Id");
                this.code = result.getString("Code");
                this.fullName = result.getString("FullName");
                this.sign = result.getString("Sign");
                return this;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "id : " + this.id + "/ " + this.code +  "/ " + this.fullName + "/ " + this.sign;
    }
}
