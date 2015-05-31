package com.company.View;

import javax.swing.*;

/**
 * Okno wczytywania aplikacji
 * Gdy zostanie nacisnięty przycisk "zaloguj" i formularza logowania nie są puste, zostaje stworzony obiekt tej klasy,
 * następnie zostaje uruchamiany nowy wątek programu który wczytuje ustawienia oraz renderuje to okno.
 * Gdy kontroler odbierze komunikat o udanym, bądź nieudanym logowaniu, okno to jest zamykane i wątek kończy działanie.
 */
public class ShowConnectionInfo implements Runnable {
    private JDialog Info;
    public Thread t;
    private ImageIcon waitImg;
    private JLabel ImageLabel;
    //----------------------------------------------------------------------------------------//

    //region Ustawienie pozycji okna oraz znajdujących sie na nim obiektów. (Konstruktor klasy i klasa wątka roboczego)
    public ShowConnectionInfo() {
        t = new Thread(this); // stworzenie nowego wątku dla tej klasy
        waitImg = new ImageIcon(Login.class.getResource("Images/loading.gif"));

    } // Konstrutkor tworzacy nowy watek oraz wczytujacy plik "loading.gif"
    public void run() {

        Info = new JDialog();
        Info.setVisible(false);
        Info.setResizable(false);
        Info.setSize(325, 80);
        Info.setLocationRelativeTo(null);
        Info.setTitle("Proszę czekać...");

        ImageLabel = new JLabel();
        ImageLabel.setIcon(waitImg);
        ImageLabel.setLayout(null);
        ImageLabel.setVisible(true);

        Info.add(ImageLabel);
    } // Klasa wątku, startowana z klasy kontrolera.
    //endregion

    public void SetTitle(String title)
    {
        ImageLabel.setText(title);
    } // Ustawienie napisu wyświetlającego się w oknie
    public void ShowDialog() {
        Info.setVisible(true);
    } // Pokazanie okienka wczytywania
    public void HideDialog()
    {
        Info.setVisible(false);
    } // Schowanie okna wczytywania
    }
