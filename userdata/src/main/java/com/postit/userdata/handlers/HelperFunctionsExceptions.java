package com.postit.userdata.handlers;

import com.postit.userdata.models.ValidationError;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HelperFunctionsExceptions {

    public List<ValidationError> getConstraintViolation(Throwable cause) {
        while((cause != null) && !(cause instanceof ConstraintViolationException)) {
            cause = cause.getCause();
        }

        List<ValidationError> listVE = new ArrayList<>();

        if (cause != null) {
            ConstraintViolationException ex = (ConstraintViolationException) cause;
            for (ConstraintViolation cv : ex.getConstraintViolations()) {
                ValidationError newVe = new ValidationError();
                newVe.setCode(cv.getInvalidValue()
                        .toString());
                newVe.setMessage(cv.getMessage());
                listVE.add(newVe);
            }
        }
        return listVE;
    }

}
