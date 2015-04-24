package com.company.View;

import com.company.ErrorType;

import javax.swing.*;

/**
 * Created by Bajtas on 2015-04-22.
 */
public class ShowMessage {
    public void setErrorType(ErrorType err_code) {
        switch (err_code.Error_){
            case EMPTY_LOGIN_AND_PASSWORD:
                ErrorMessage("Proszę podać login i hasło.");
                break;
            case EMPTY_LOGIN:
                ErrorMessage("Proszę podać login.");
                break;
            case EMPTY_PASSWORD:
                ErrorMessage("Proszę podać hasło.");
                break;
            case ERROR_WITH_DB_CONNECTION:
                ErrorMessage("Wystąpił błąd połączenia z bazą danych. Prosimy spróbować ponownie.");
                break;
            case EMPTY_LOGIN_PASSWORD_AND_EMAIL:
                ErrorMessage("Proszę najpierw wypełnić wymagane pola.");
                break;
            case EMPTY_EMAIL:
                ErrorMessage("Proszę podać adres email.");
                break;
            case EMPTY_PASSWORD_AND_EMAIL:
                ErrorMessage("Proszę podać adres email i hasło.");
                break;
            case LOGIN_NOT_ALLOWED:
                ErrorMessage("<html>Login zawiera niedowzwolone znaki:<br> ~!@#$%^&&&&*()_+-={}[]:|'';'\\<>?,./<br>Proszę wpisać login bez spacji i powyższych znaków.</html>");
                break;
            case PASSWORD_TOO_SHORT:
                ErrorMessage("Podane hasło jest za krótkie min. 5 znaków.");
                break;
            case PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS:
                ErrorMessage("<html>Hasło może zawierać jedynie duże i małe litery, <br>cyfry oraz znaki specjalne: $&%#@!*<br>Proszę wpisać poprawne hasło.</html>");
                break;
            case EMAIL_WRONG_INPUT:
                ErrorMessage("<html>Proszę wpisać adres email wraz ze znakiem @ i domeną np.<br><center><i>jan.kowalski@interia.eu</i></center></html>");
                break;
            case USER_ALREADY_EXIST:
                ErrorMessage("<html>Użytkownik o podanym loginie jest już w bazie<br>Proszę podać inny login i spróbować ponownie.</html>");
                break;
            case EMAIL_ALREADY_EXIST:
                ErrorMessage("Podany email istnieje już w bazie. Prosimy podać inny adres email.");
                break;
            default:
                ErrorMessage("Wystąpił nieznany błąd.");
                break;
        }

    } // Klasa która odczytuje kod błędu a następnie przekazuje odpowiednią informacje do wyswietlenia
    private void ErrorMessage(String ErrMessage) {
        JOptionPane.showMessageDialog(null, ErrMessage, "Komunikat", JOptionPane.INFORMATION_MESSAGE);
    } // Wyswietlenie komunikatow błędów
}