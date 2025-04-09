import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {
    public List<Entity> loadData(String path) {
        List<Entity> entities = new ArrayList<>();
        String text = "";
        int label = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length == 2) {
                    text += " " + tokens[0].trim();
                    label = Integer.parseInt(tokens[1].trim());
                    entities.add(new Entity(text.toString().trim(), label));
                    text = "";
                } else {
                    text += " " + line.trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
