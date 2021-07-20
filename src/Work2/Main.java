package Work2;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    private static final String PATH = "/Users/tenilin/Games/savegames/";

    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(50, 2, 22, 4);
        GameProgress progress2 = new GameProgress(10, 1, 7, 8);
        GameProgress progress3 = new GameProgress(99, 10, 29, 100);

        saveGame("save3.dat", progress3);
        saveGame("save1.dat", progress1);
        saveGame("save2.dat", progress2);

        zipFiles("save.zip", "save3.dat", "save1.dat", "save2.dat");

        openZip("save.zip", PATH);

        System.out.println(openProgress("save3.dat"));
    }

    public static void saveGame(String fileName, GameProgress progress) {
        String path = PATH + fileName;
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String zipFileName, String... file) {
        String zipPath = PATH + zipFileName;
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (int i = 0; i < file.length; i++) {
                String filePath = PATH + file[i];
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    ZipEntry entry = new ZipEntry(file[i]);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                new File(filePath).delete();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openZip(String file, String path) {
        String unzipPath = PATH + file;
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(unzipPath))){
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(path + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String fileProgress) {
        String pathFileProgress = PATH + fileProgress;
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(pathFileProgress);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gameProgress;
    }
}
