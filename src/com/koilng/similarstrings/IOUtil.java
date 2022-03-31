package com.koilng.similarstrings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {

  private static final String resourcePath = "/resources/";

  private static Path getPath(String filename) {
    return Paths.get(Paths.get("").toAbsolutePath() + resourcePath + filename);
  }

  /*private static String getPath(String filename) {
    Path currentAbsolutePath = Paths.get("").toAbsolutePath();
    return currentAbsolutePath + resourcePath + filename;
  }*/

  private static String[] readToArray(BufferedReader reader, int count) throws IOException {
    String[] str = new String[count];
    for (int i = 0; i < count; i++) {
      str[i] = reader.readLine();
    }
    return str;
  }

  public static List<String[]> readFile(String filename) {
    List<String[]> strings = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getPath(filename).toAbsolutePath().toString()))) {
      int firstCount = Integer.parseInt(bufferedReader.readLine());
      String[] firstStr = readToArray(bufferedReader, firstCount);

      int secondCount = Integer.parseInt(bufferedReader.readLine());
      String[] secondStr = readToArray(bufferedReader, secondCount);

      strings.add(firstStr);
      strings.add(secondStr);

    } catch (IOException e) {
      System.out.println("Wrong input");
    }
    return strings;
  }

  public static void writeFile(String filename, String line) {
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(getPath(filename), StandardOpenOption.TRUNCATE_EXISTING)) {
      bufferedWriter.write(line);
      bufferedWriter.newLine();

    } catch (IOException e) {
      System.out.println("Wrong output filename");
    }
  }
}
