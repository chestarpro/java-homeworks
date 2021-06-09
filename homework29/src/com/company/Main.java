package com.company;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("file.txt")) {
            writeCode(fileWriter);
            fileWriter.close();
            checkFileLength(new File("file.txt"));
        } catch (IllegalStateException | IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileReader fileReader = new FileReader("file.txt");
             FileWriter fileWriter1 = new FileWriter("file2.txt")) {
            Scanner sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                fileWriter1.write(sc.nextLine().replaceAll("\\s+",""));
            }
            fileReader.close();
            fileWriter1.close();
            checkFileLength(new File("file2.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не доступен");
        }
        catch (NullPointerException | IllegalStateException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkFileLength(File file) {
        long size = file.length();
        if (size > 100)
        throw new IllegalStateException(file.getName() + " файл слишком большой, размер: " + size + " байт.");
    }

    public static void writeCode(FileWriter fileWriter) throws IOException {
        URL oracle = new URL("https://raw.githubusercontent.com/chestarpro/Java-HomeWorks/homework29/myCode.txt");
        Scanner scanner = new Scanner(oracle.openStream());
        while (scanner.hasNextLine()) {
            fileWriter.write(scanner.nextLine() + "\n");
        }
    }
}
