import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimilarStrings {

  public static void main(String[] args) {
    Scanner stringScanner = new Scanner(System.in);

    int first = Integer.parseInt(stringScanner.nextLine());

    String[] firstStrings = new String[first];
    for (int i = 0; i < firstStrings.length; i++) {
      firstStrings[i] = stringScanner.nextLine();
    }

    int second = Integer.parseInt(stringScanner.nextLine());

    String[] secondStrings = new String[second];
    for (int i = 0; i < secondStrings.length; i++) {
      secondStrings[i] = stringScanner.nextLine();
    }

    findSimilar(firstStrings, secondStrings);
  }

  static void findSimilar(String[] strings1, String[] strings2) {
    Map<String, String> usedElements = new HashMap<>();

    if (strings1 == null || strings2 == null) {
      throw new IllegalArgumentException("Empty arrays");
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
        System.out.println(firstStr + ":" + mostSimilarString);
      } else {
        System.out.println(firstStr + ":?");
      }

    }
  }
}

