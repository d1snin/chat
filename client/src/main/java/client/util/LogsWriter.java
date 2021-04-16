package client.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LogsWriter {

    private final File file;
    private FileWriter writer;

    public LogsWriter(String filename) {
        file = new File(filename);

    }

    public void writeFile(String text) {

        try {
            writer = new FileWriter(file, true);
            if (file.createNewFile()) {
                System.out.println(file.getName() + "created");
                writer.append(text);
                writer.append(System.getProperty("line.separator"));
                writer.flush();
            } else {
                writer.append(text);
                writer.append(System.getProperty("line.separator"));
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLastLines() throws IOException {
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            for (int i = 0; i < (Math.min(lines.size(), 100)); i++) {
                sb.append(lines.get(i)).append("\n");
            }

            return sb.toString();
        } else {
            return null;
        }
    }
}
