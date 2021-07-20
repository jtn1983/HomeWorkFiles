package Work1;

import javax.imageio.IIOException;
import java.io.*;

public class Main {
    private static final String PATH = "/Users/tenilin/Games/";
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        createDir(log, "src", "res", "savegames", "temp" ,
                "src/main", "src/test", "res/drawables", "res/vectors", "res/icons");

        createFile(log, "src/main/Main.java", "src/main/Utils.java", "temp/temp.txt");

        writeLog(log,"temp/temp.txt");

    }

    public static void createDir (StringBuilder log, String ... dirNames) {
        for (String dirName : dirNames) {
            File dir = new File(PATH + dirName);
            if (dir.mkdir()) {
                log.append("Directory ").append(dirName).append(" created\n");
            }
        }
    }

    public static void createFile (StringBuilder log, String ... fileNames) {
        for (String fileName : fileNames) {
            File file = new File(PATH + fileName);
            try {
                if (file.createNewFile()) {
                    log.append("File ").append(fileName).append(" created\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void writeLog(StringBuilder log, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName))){
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
