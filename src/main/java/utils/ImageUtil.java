package utils;

import models.bean.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ImageUtil {
    private static final String pathToSavedImages = System.getenv("pathToSavedImages");

    public static boolean deleteImages(List<Image> images) {
        try {
            for (Image image : images) {
                File file = new File(pathToSavedImages + File.separator + image.getPath().replace("public/", ""));
                file.delete();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<File> getFilesFromImages(List<Image> images) {
        List<File> files = new ArrayList<>();
        for (Image image : images) {
            File file = new File(pathToSavedImages + File.separator + image.getPath().replace("public/", ""));
            if (file.exists())
                files.add(file);
        }
        return files;
    }

    public static File compressFile(List<File> files, String pathZipFile) {

        try {
            File zip = new File(pathZipFile);
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));

            byte[] buffer = new byte[1024];
            for (File file : files) {
                FileInputStream fis = new FileInputStream(file);

                zos.putNextEntry(new ZipEntry(file.getName()));

                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                zos.closeEntry();
                fis.close();
            }
            zos.close();
            return zip;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
