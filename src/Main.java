import java.io.File;
import java.util.Scanner;

public class Main {
    public static void walk(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        for (File file : list) {
            if (file.isDirectory()) {
                walk(file.getAbsolutePath());
            } else {
                if (javaOrKtExpansion(file.getName())) {
                    renameFile(file);
                }
            }
        }
    }

    public static void renameFile(File file) {
        String fileName = file.getAbsolutePath();
        String newFileName = new StringBuffer(fileName).insert(fileName.lastIndexOf("."), ".2019").toString();
        File newNameFile = new File(newFileName);
        if (file.renameTo(newNameFile)) {
            System.out.println("File:" + newNameFile.getAbsoluteFile());
        } else System.out.println(file.getAbsolutePath() + " File not rename.");
    }

    public static boolean javaOrKtExpansion(String fileName) {
        String[] fileNameSplit = fileName.split("\\.");
        if (fileNameSplit[fileNameSplit.length - 1].equals("java") || fileNameSplit[fileNameSplit.length - 1].equals("kt"))
            return true;
        else return false;
    }

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        String directory = "";
        System.out.println("Please write rename directory: ");
        directory = scaner.nextLine();
        System.out.println("Renamed files:");
        Main.walk(directory);
    }
}
