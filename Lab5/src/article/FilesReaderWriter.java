package article;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class FilesReaderWriter {
    public File fileReader;
    private FileWriter fileWriter;
    public PrintWriter filePrint;
    public List<StringBuilder> fileLines;

    public FilesReaderWriter() {

    }

    private void loadFile() throws Exception {
        Scanner scanner = new Scanner(fileReader);

        while(scanner.hasNext()) {
            fileLines.add(new StringBuilder(scanner.nextLine()));
        }
    }

    public void readFile()  {
        fileLines = new ArrayList<>();
        try {
            loadFile();
        } catch (Exception e) {
            System.err.println("File  path = " + fileReader.getPath() + "Not Found!!");
        }

    }

    public FilesReaderWriter(String path) {
        fileReader = new File(path);
    }

    public void setReadFilePath(String path) {
        fileReader = new File(path);
    }


    public void setWriteFilePath(String path) {
        try {
            fileWriter = new FileWriter(path);
            filePrint = new PrintWriter(fileWriter);
        } catch (Exception e) {
            System.err.println("File  path = " + path + "Not Found!!");
        }
    }

    public void printIntoTheFile(StringBuilder[] data) {
        for (StringBuilder line : data)
            filePrint.println(line);
        filePrint.close();
    }

    /**
     * Same idea like in the method below but plus U can write a array of lines.
     * In a bonus file close automatic.
     * @param format
     * @param arg
     */
    public void printIntoTheFile(StringBuilder[] format, Object[] arg) {
        for (int i = 0; i < format.length; i++)
            filePrint.printf(format[i].toString(), arg[i]);
        filePrint.close();
    }


    public void printIntoTheFileLineByLine (StringBuilder line) {
        filePrint.println(line.toString());
    }

    /**
     * This method exists just for printing into a file line or some string with using standard printf method.
     * Let's make a Example:
     * @param format    Let's set into a form that, "It's my line. Author is %s."
     * @param arg       And into arg it will be new String("Yushchenko Andrew")
     *
     * And after this manipulation we will get this It's my line. Author is Yushchenko Andrew.
     */
    public void printIntoTheFileLineByLine (StringBuilder format, Object arg) {
        filePrint.printf(format.toString(), arg);
    }

    /**
     * This method needs only for closing already opened file in the method above.
     */
    public void closeOpenFile() {
        filePrint.close();
    }
}
