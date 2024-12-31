package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.util.Stack;

public abstract class AbstractCommand implements Command {

  protected String menuTitle;

  public AbstractCommand(String menuTitle) {
    this.menuTitle = menuTitle;
  }

  private void printMenus() {
    String[] menus = getMenus();
    System.out.printf("[%s]\n", menuTitle);
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("%d. %s\n", (i + 1), menus[i]);
    }
    System.out.println("9. 이전");
  }

  protected abstract String[] getMenus();


  @Override
  public void execute(Stack menuPath) {
    menuPath.push(menuTitle);

    printMenus();

    while (true) {
      String command = Prompt.input("메인/%s>", menuTitle);
      if (command.equals("menu")) {
        printMenus();
        continue;
      } else if (command.equals("9")) {
        return;
      }

      try {
        int menuNo = Integer.parseInt(command);
        String menuName = getMenuTitle(menuNo);
        if (menuName == null) {
          System.out.println("올바른 메뉴 번호가 아닙니다.");
          continue;
        }

        processMenu(menuName);

      } catch (NumberFormatException e) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  protected abstract void processMenu(String menuName);

  private String getMenuTitle(int menuNo) {
    String[] menus = getMenus();
    return isValidateMenu(menuNo) ? menus[menuNo - 1] : null;
  }

  private boolean isValidateMenu(int menuNo) {
    String[] menus = getMenus();
    return menuNo >= 1 && menuNo <= menus.length;
  }

  private String getMenuPathTitle(Stack menuPath) {
    StringBuilder strBuilder = new StringBuilder();
    for (int i = 0; i < menuPath.size(); i++) {
      if (!strBuilder.isEmpty()) {
        strBuilder.append("/");
      }
      strBuilder.append(menuPath.get(i));
    }

    return strBuilder.toString();
  }

}
