package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currencies {
    private int id;
    private String code;
    private String fullName;
    private String sign;


    public Currencies(){
    }


    @Override
    public String toString() {
        return "id : " + this.id + "/ " + this.code + "/ " + this.fullName + "/ " + this.sign;
    }
}
