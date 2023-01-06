package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;

public class Model {
    public void saveChanges(File[] file, String[] newNames) throws IOException {
        Arrays.sort(file, Comparator.comparing(o -> o.getName().substring(o.getName().length() - 8)));
        for (int i = 0; i < file.length; i++) {
            Files.copy(file[i].toPath(),
                    Path.of(file[i].getPath().substring(0, file[i].getPath().lastIndexOf("\\"))+ "\\" + newNames[i]),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
