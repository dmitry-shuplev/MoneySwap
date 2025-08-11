package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currencies {
    private int id;
    private String code;
    private String name;
    private String sign;


    public Currencies() {
    }



}
