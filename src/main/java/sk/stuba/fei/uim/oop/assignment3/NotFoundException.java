package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "object not found")
public class NotFoundException extends RuntimeException{
}
