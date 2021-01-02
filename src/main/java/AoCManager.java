import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class AoCManager {

    public static List<String> getInput(int i) {
        try {
            return Files.readAllLines(Path.of("./src/main/resources/Day" + i + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
