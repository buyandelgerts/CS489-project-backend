package backend.project.advice;

import backend.project.exception.*;
import org.slf4j.*;
import org.springframework.http.*;
import backend.project.dto.dto.ErrorDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleAuthenticationError(AuthenticationException ex) {
        logger.error("User not found: " + ex.getMessage());
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleUsernameNotFound(UsernameNotFoundException ex) {
        logger.error("User not found: " + ex.getMessage());
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleProductNotFound(ProductNotFoundException ex) {
        logger.error("Product not found: " + ex.getMessage());
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(OrderFailedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDTO handleOrderFailedError(OrderFailedException ex) {
        logger.error("Order failed: " + ex.getMessage());
        return new ErrorDTO(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleAlreadyExist(AlreadyExistException ex) {
        logger.error("User already exist: " + ex.getMessage());
        return new ErrorDTO(HttpStatus.CONFLICT.value(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleAllException(Exception ex){
        logger.error("Unknown error occurred: ", ex);
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred", System.currentTimeMillis());
    }
}
