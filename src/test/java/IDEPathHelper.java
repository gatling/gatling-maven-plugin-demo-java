import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class IDEPathHelper {

  static final Path mavenSourcesDirectory;
  static final Path mavenResourcesDirectory;
  static final Path mavenBinariesDirectory;
  static final Path resultsDirectory;
  static final Path recorderConfigFile;

  static {
      mavenBinariesDirectory = Arrays.stream(System.getProperty("java.class.path").split(File.pathSeparator))
              .filter(cpe -> cpe.endsWith("test-classes"))
              .map(cpe -> Path.of(cpe))
              .findFirst()
              .get();
      Path mavenTargetDirectory = mavenBinariesDirectory.getParent();
      Path projectRootDir = mavenTargetDirectory.getParent();
      Path mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test");

      mavenSourcesDirectory = mavenSrcTestDirectory.resolve("java");
      mavenResourcesDirectory = mavenSrcTestDirectory.resolve("resources");
      resultsDirectory = mavenTargetDirectory.resolve("gatling");
      recorderConfigFile = mavenResourcesDirectory.resolve("recorder.conf");
  }
}
