package pl.pansoft.prime.result;

import pl.pansoft.prime.dto.PrimeDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrimeSaver {

    public static String saveToFile(PrimeDto primeDto)  {
        String fileName = System.currentTimeMillis() + ".xml";

        File file = new File(fileName);
        String path = file.getAbsolutePath();

        try (PrintWriter out = new PrintWriter(path)) {
            out.println(primeDto.toXml());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return path;
    }
}
