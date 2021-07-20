package Work1;

import javax.imageio.IIOException;
import java.io.*;

public class Main {
    private static final String directory = "/Users/tenilin/Games";
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        File dirSrc = new File(directory, "src");
        if (dirSrc.mkdir()) {
            log.append("Directory src created\n");
        }

        File dirRes = new File(directory, "res");
        if(dirRes.mkdir()) {
            log.append("Directory res created\n");
        }

        File dirSavegames = new File(directory, "savegames");
        if(dirSavegames.mkdir()) {
            log.append("Directory savegames created\n");
        }

        File dirTemp = new File(directory, "temp");
        if(dirTemp.mkdir()) {
            log.append("Directory temp created\n");
        }

        File dirMain = new File(dirSrc, "main");
        if(dirMain.mkdir()) {
            log.append("Directory main in src created\n");
        }

        File dirTest = new File(dirSrc, "test");
        if(dirMain.mkdir()) {
            log.append("Directory test in src created\n");
        }

        File fileMain = new File(dirMain, "Main.java");
        try {
            if (fileMain.createNewFile()) {
                log.append("File Main.java created in main directory\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File fileUtils = new File(dirMain, "Utils.java");
        try {
            if (fileUtils.createNewFile()) {
                log.append("File Utils.java created in main directory\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File dirDrawables = new File(dirRes, "drawables");
        if(dirDrawables.mkdir()) {
            log.append("Directory drawables in res created\n");
        }

        File dirVectors = new File(dirRes, "vectors");
        if(dirVectors.mkdir()) {
            log.append("Directory vectors in res created\n");
        }

        File dirIcons = new File(dirRes, "icons");
        if(dirIcons.mkdir()) {
            log.append("Directory icons in res created\n");
        }

        File fileTemp = new File(dirTemp, "temp.txt");
        try {
            if (fileTemp.createNewFile()) {
                log.append("File temp.txt created in temp directory\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemp))){
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }


    }
}
