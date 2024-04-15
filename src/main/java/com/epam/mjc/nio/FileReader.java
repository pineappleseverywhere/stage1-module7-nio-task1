package com.epam.mjc.nio;


import java.io.*;

interface SimpleLogger {
    void log(String message);
}

class ConsoleLogger implements SimpleLogger {
    public void log(String message) {
        System.out.println(message);
    }
}

public class FileReader {

    private final SimpleLogger logger;

    public FileReader() {
        this(new ConsoleLogger());
    }

    public FileReader(SimpleLogger logger) {
        this.logger = logger;
    }

    public Profile getDataFromFile(File file) { // Accept File argument
        Profile profile = new Profile();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String key = parts[0];
                String value = parts[1];
                switch (key) {
                    case "Name":
                        profile.setName(value);
                        break;
                    case "Age":
                        profile.setAge(Integer.parseInt(value));
                        break;
                    case "Email":
                        profile.setEmail(value);
                        break;
                    case "Phone":
                        profile.setPhone(Long.parseLong(value));
                        break;
                    default:
                        logger.log("Unknown key encountered: " + key);
                }
            }
        } catch (FileNotFoundException e) {
            logger.log("File not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.log("Error reading file: " + e.getMessage());
        }
        return profile;
    }
}
class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        File file = new File("C:\\Users\\2RS\\stage1-module6-io-task1\\src\\main\\resources\\Profile.txt");
        Profile profile = fileReader.getDataFromFile(file); // Pass the File object
        System.out.println(profile);
    }
}



