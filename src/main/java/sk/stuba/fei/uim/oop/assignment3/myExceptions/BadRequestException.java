package sk.stuba.fei.uim.oop.assignment3.myExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason= "cart payed or not enough product")
public class BadRequestException extends RuntimeException{
}
