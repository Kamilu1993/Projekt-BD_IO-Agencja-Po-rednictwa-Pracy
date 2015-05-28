package com.company.Model;

import com.company.Model.ImageService.ImageFilter;
import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Bajtas on 2015-05-23.
 */
public class PhotoService extends JPanel{
    PhotoService(){
        Photo = null;
        ChooseFileAction = 0;
    }

    private byte[] bArrayFile=null;
    private File Photo;
    private int ChooseFileAction;
    public void ShowFileDialog(){
        // Stworzenie obiektu do wybierania plików
        final JFileChooser chooseFile = new JFileChooser();
        // Można wybrać tylko pliki
        chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Dodanie do obiektu filtra, wyświetlajacego tylko pliki graficzne.
        chooseFile.addChoosableFileFilter(new ImageFilter());
        chooseFile.setAcceptAllFileFilterUsed(false);
        // Pokazanie okna do wybrania pliku.
        ChooseFileAction = chooseFile.showOpenDialog(this);

        // Nacisniecie przycisku "Otwórz"
        if (ChooseFileAction == JFileChooser.APPROVE_OPTION) {
            // Przypisanie zmiennej plikowej do wybranego pliku
            Photo = chooseFile.getSelectedFile();
            System.out.println("Otwieram: "+Photo.getName());
            try {
                // Zczytanie z pliku do zmiennej bufurującej obrazy
                BufferedImage img = ImageIO.read(Photo);
                System.out.println("Zdjęcie wczytano poprawnie!");
                // Sprawdzenie wysk. i szer. zdjęcia
                if (img.getWidth() > 185 || img.getHeight() > 185) {
                    System.out.println("Plik: " + Photo.getName() + " Szerokość: " + img.getWidth() + " Wysokość: " + img.getHeight());
                    System.out.println("Skalowanie zdjęcia...");
                    //Skalowanie zdjecia, dodatkowa biblioteka
                    img = Scalr.resize(img, 280,200);
                    //Nowy strumień wyjścia
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    //Zapisanie zdjecia "img"(BufferedImage) do strumienia "baos"
                    ImageIO.write(img, "jpg", baos);
                    //Przypisanie wyniku do tablicy bitów
                    bArrayFile = baos.toByteArray();
                    baos.close();
                }
            } catch (IOException e){
                System.out.println("Wystąpił błąd podczas wczytywania zdjęcia!");
            }
        }
        else {
            System.out.println("Anulowano otwieranie pliku.");
        }
    }
    final public byte[] getImageByteArray(){
        return bArrayFile;
    } // metoda zwracająca tablice bitów
}
