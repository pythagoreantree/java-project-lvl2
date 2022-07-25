package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class DifferTest {

    /*
    * All test scripts should be got from resources
    * */
    @Test
    void testJson() {
        try {
            String res = Files.readString(Paths.get("result_stylish.txt"));
            String ans = Differ.generate("file1.json", "file2.json");
            Assertions.assertTrue(ans.equals(res));
        } catch (IOException e) {
            System.out.println("Can't open file");
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

//    @Test
    void testJsonComplex() {
        try {
            String res = Files.readString(Paths.get("result_stylish_complex.txt"));
//            String ans = Differ.generate("file3.json", "file4.json");
            System.out.println(res);
            System.out.println();
//            System.out.println(ans);

//            Assertions.assertTrue(ans.equals(res));
        } catch (IOException e) {
            System.out.println("Can't open file");
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testYml() {
        try {
            String res = Files.readString(Paths.get("result_stylish.txt"));
            String ans = Differ.generate("file1.yml", "file2.yml");
            Assertions.assertTrue(ans.equals(res));
        } catch (IOException e) {
            System.out.println("Can't open file");
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}
