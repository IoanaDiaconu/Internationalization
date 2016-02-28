package tutorial.internationalization.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tutorial.internationalization.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ioana on 2/28/2016.
 */
@Component
public class UserFormValidator implements Validator {
    private static final String nameRegex = "^[\\p{L} .'-]+$";
    private static final Pattern pattern = Pattern.compile(nameRegex,Pattern.CASE_INSENSITIVE);

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        validateName(user.getName(),errors);
        validateCountry(user.getCountry(),errors);
    }

    private void validateName(String name, Errors errors) {
        Matcher matcher = pattern.matcher(name);
        if (!matcher.find()){
            errors.rejectValue("name","Name not ok");
        }
    }

    private void validateCountry(String country, Errors errors) {
        if(!country.matches( "([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")){
            errors.rejectValue("country","Country not ok");
        }
    }


}
