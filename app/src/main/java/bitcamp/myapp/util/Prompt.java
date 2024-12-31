package bitcamp.myapp.util;

import java.util.Scanner;

public class Prompt {

  public static Scanner sc = new Scanner(System.in);
  static Queue inputQueue = new Queue();

  public static String input(String format, Object... args) {
    String promptTitle = String.format(format, args);
    System.out.printf(promptTitle);

    String input = sc.nextLine();
    if (format.endsWith(">")) {
      inputQueue.offer(promptTitle + input);
      if (inputQueue.size() > 20) {
        inputQueue.poll();
      }
    }
    return input;
  }

  public static int inputInt(String title) {
    return Integer.parseInt(input(title));
  }

  public static void close() {
    sc.close();
  }

  public static void printHistory() {
    System.out.println("[명령 내역]----------------------");
    for (int i = 0; i < inputQueue.size(); i++) {
      System.out.println(inputQueue.get(i));
    }
    System.out.println("--------------------------- 끝");
  }
}
