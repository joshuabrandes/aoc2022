import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        var input = readFileToString("");
        var splitInElves = input.split("\n");

        System.out.println(splitInElves.length);
    }

    String readFileToString(String fileName) throws IOException {
        try(FileInputStream inputStream = new FileInputStream("foo.txt")) {
            String everything = IOUtils.toString(inputStream);
            // do something with everything string
        }

    }
}
