package g53298.humbug;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import g53298.humbug.model.Position;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author israelmeiresonne
 */
public class testJson {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/data/position.json");
        JsonNode jsonNode = mapper.readTree(file);
        System.out.println("file exist: " + file.exists());
        System.out.println(jsonNode);
                
        Position position = mapper.readValue(file, Position.class);
        System.out.println("Position row: " + position.getRow());
        System.out.println("Position column: " + position.getColumn());
    }
}
