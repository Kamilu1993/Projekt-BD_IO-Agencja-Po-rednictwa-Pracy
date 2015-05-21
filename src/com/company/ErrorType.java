package com.company;

/**
 * Typy występujacych błędów
 */
public class ErrorType {
    public enum ErrTypes{
        NO_ERRORS, EMPTY_LOGIN_AND_PASSWORD, EMPTY_LOGIN, EMPTY_PASSWORD, ERROR_WITH_DB_CONNECTION,
        EMPTY_LOGIN_PASSWORD_AND_EMAIL, EMPTY_PASSWORD_AND_EMAIL, EMPTY_EMAIL, USER_ALREADY_EXIST, UNKNOWN_ERROR,
        EMAIL_ALREADY_EXIST, LOGIN_NOT_ALLOWED, PASSWORD_TOO_SHORT, PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS,
        EMAIL_WRONG_INPUT, EMPTY_LOGIN_AND_EMAIL, THIS_IS_CUSTOMER_ACC, THIS_IS_EMPLOYEE_ACC, THIS_IS_ADMIN_ACC,
        ERROR_WITH_USER_ROLE, WRONG_PASSWORD, REGISTER_SUCCESS, USER_DOESNT_EXIST, PASSWORD_DOESNT_MATCH
    }
    public ErrTypes Error_;
}
