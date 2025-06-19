package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Currensie {
    private static Connection connection = DBUtils.getConnection();
    private PreparedStatement pstmt;
    private int id;
    private String code;
    private String fullName;
    private String signe;

    public Currensie getByCode(String code) {
        String queryTemplate = "SELECT INTO Currencies (Code) VALUES(?)";
        try{
            pstmt = connection.prepareStatement(queryTemplate);
            pstmt.setString(1, code);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                this.id = result.getInt("Id");
                this.code = result.getString("Code");
                this.fullName = result.getString("FullName");
                this.signe = result.getString("Sign");
                return this;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "id : " + id;
    }
}
