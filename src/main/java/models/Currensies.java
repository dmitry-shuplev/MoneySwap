package models;

public class Currensies {
    private int id;
    private String code;
    private String fullName;
    private String sign;


    public Currensies(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }



    @Override
    public String toString() {
        return "id : " + this.id + "/ " + this.code + "/ " + this.fullName + "/ " + this.sign;
    }
}
