package by.itacademy.callservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.itacademy.callservice.utill.Constants.CLOSE_BRACKET;
import static by.itacademy.callservice.utill.Constants.FULL_NUMBER_LENGTH;
import static by.itacademy.callservice.utill.Constants.LOCAL_NUMBER_LENGTH;
import static by.itacademy.callservice.utill.Constants.NON_DIGIT;
import static by.itacademy.callservice.utill.Constants.OPEN_BRACKET;
import static by.itacademy.callservice.utill.Constants.VOID;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        phone = phone.replaceAll(NON_DIGIT, VOID);

        return isBracketsRight(phone)
                && phone.length() >= LOCAL_NUMBER_LENGTH
                && phone.length() <= FULL_NUMBER_LENGTH;
    }

    private boolean isBracketsRight(String phone) {
        Deque<Character> brackets = new ArrayDeque<>();
        for (int i = 0; i < phone.length(); i++) {
            char symbol = phone.charAt(i);
            if (symbol == OPEN_BRACKET) {
                brackets.add(symbol);
            } else if (symbol == CLOSE_BRACKET) {
                if (brackets.isEmpty()) {
                    return false;
                } else if (brackets.getLast() == OPEN_BRACKET) {
                    brackets.removeLast();
                } else {
                    return false;
                }
            }
        }
        return brackets.isEmpty();
    }
}
