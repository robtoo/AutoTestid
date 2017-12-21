

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileControllerTests {
    private String filePath;
    private FileController fileController;

    @Before public void init(){
        filePath = "C:\\Users\\rober_000\\workspace1\\WeatherForecast\\src\\main\\Resources\\input.txt";
        fileController = new FileController();
    }

    @Test
    public void doesFileReaderReadFile () throws IOException {

        List<String> linesInFile = fileController.fileReader(filePath);

        assertEquals("Tallinn", linesInFile.get(0));
    }

    @Test
    public void doesFileReaderReadFileFromRightocation () throws IOException {
        assertEquals("C:\\Users\\rober_000\\workspace1\\WeatherForecast\\src\\main\\Resources\\input.txt", filePath);
    }



}
