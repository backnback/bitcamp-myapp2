package bitcamp.myapp.util;

import java.util.Scanner;

public class Prompt {

  public static Scanner sc = new Scanner(System.in);

  public static String input(String format, Object... args) {
    System.out.printf(format + " ", args);
    return sc.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(input(title));
  }


  public static void close() {
    sc.close();
  }
}
