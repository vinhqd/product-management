package com.vinhqd.app.validations;

import com.vinhqd.app.anotations.ValidUserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<ValidUserName, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){3,30}[a-zA-Z0-9]$";

    @Override
    public void initialize(ValidUserName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context){
        return (validateUserName(userName));
    }

    private boolean validateUserName(String userName) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(userName);
        return matcher.matches();
    }

}
