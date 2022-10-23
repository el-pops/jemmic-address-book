package popov.ilia.utils;

import popov.ilia.entities.Contact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

public class FileUtils {

    public static List<String> readFromFile() throws IOException {
        File file = new File("contacts.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        return Files.readAllLines(file.toPath());
    }

    public static void save(Set<Contact> contacts) throws IOException {
        File tempFile = new File("temporary.txt");
        tempFile.createNewFile();

        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            for (Contact contact : contacts) {
                writer.write(MappingUtils.mapPersonToString(contact));
                writer.newLine();
            }
        }
        File mainSaveFile = new File("contacts.txt");
        File backupFile = new File("backup.txt");
        if (mainSaveFile.exists()) {
            mainSaveFile.renameTo(backupFile);
        }
        tempFile.renameTo(mainSaveFile);
        backupFile.delete();
        tempFile.delete();
    }
}
