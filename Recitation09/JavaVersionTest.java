import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaVersionTest {
    @Test
    public void testJavaVersion(){
    String version = System.getProperty("java.version");
    System.out.println("Java Version: " + version);
    assertTrue(version != null && !version.isEmpty(), "Java version should be present.");
    }
}