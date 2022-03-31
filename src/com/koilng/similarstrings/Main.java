package com.koilng.similarstrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    List<String[]> stringsArrayList = IOUtil.readFile("input.txt");
    String toWrite = findSimilar(stringsArrayList.get(0), stringsArrayList.get(1));
    IOUtil.writeFile("output.txt", toWrite);
  }

  static String findSimilar(String[] strings1, String[] strings2) {
    Map<String, String> usedElements = new HashMap<>();
    StringBuilder builder = new StringBuilder();

    if (strings1 == null || strings2 == null) {
      throw new IllegalArgumentException("Empty arrays");
    }

    if (strings1.length == 1 && strings1.length == strings2.length) {
      return strings1[0] + ":" + strings2[0];
    }

    for (String firstStr : strings1) {
      String[] currentString = firstStr.split(" ");
      int similarWordsCount = 0;
      String mostSimilarString = null;

      for (String secondStr : strings2) {

        if (usedElements.get(secondStr) != null) {
          continue;
        }

        int currentSimilarWordsCount = 0;

        for (String elementOfFirst : currentString) {
          if (secondStr.contains(elementOfFirst)) {
            currentSimilarWordsCount++;
          }
        }

        if (similarWordsCount < currentSimilarWordsCount) {
          similarWordsCount = currentSimilarWordsCount;
          mostSimilarString = secondStr;
        }

      }

      if (mostSimilarString != null) {
        usedElements.put(mostSimilarString, mostSimilarString);
        builder.append(firstStr).append(":").append(mostSimilarString).append("\n");
      } else {
        builder.append(firstStr).append(":?").append("\n");
      }
    }

    Arrays.stream(strings2)
        .filter(e -> !usedElements.containsKey(e))
        .forEach(e -> builder.append(e).append(":?").append("\n"));

    return builder.toString();
  }
}
