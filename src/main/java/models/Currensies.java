package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currensies {
    private int id;
    private String code;
    private String fullName;
    private String sign;


    public Currensies(){
    }


    @Override
    public String toString() {
        return "id : " + this.id + "/ " + this.code + "/ " + this.fullName + "/ " + this.sign;
    }
}
