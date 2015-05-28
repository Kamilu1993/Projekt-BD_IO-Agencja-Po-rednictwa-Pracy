package com.company.Model.ImageService;

import java.io.File;

/**
 * Created by Bajtas on 2015-05-23.
 */
public class Utils {

    public static String jpeg = "jpeg";
    public static String jpg = "jpg";
    public static String gif = "gif";
    public static String tiff = "tiff";
    public static String tif = "tif";
    public static String png = "png";

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
