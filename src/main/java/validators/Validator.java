package validators;

import java.util.regex.Pattern;
public class Validator {
    public boolean isCodeValid(String code){
        String regex = "^[A-Z]{3}$";
        return Pattern.matches(regex, code);
    }
}
