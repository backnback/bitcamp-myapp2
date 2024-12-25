package bitcamp.myapp.command;

import bitcamp.myapp.util.LinkedList;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.User;

import static bitcamp.myapp.util.Prompt.input;
import static bitcamp.myapp.util.Prompt.inputInt;


public class UserCommand extends AbstractCommand {

  LinkedList userList = new LinkedList();
  private String[] menus = {"등록", "목록", "조회", "변경", "삭제"};

  public UserCommand(String menuTitle) {
    super(menuTitle);
  }


  @Override
  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
      case "등록":
        addUser();
        break;
      case "목록":
        listUser();
        break;
      case "조회":
        viewUser();
        break;
      case "변경":
        updateUser();
        break;
      case "삭제":
        deleteUser();
        break;
    }
  }

  private void addUser() {
    User user = new User();
    user.setName(Prompt.input("이름?"));
    user.setEmail(Prompt.input("이메일?"));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처?"));
    user.setNo(User.getNextSeqNo());
    userList.add(user);
  }

  private void listUser() {
    System.out.println("번호 이름 이메일");
    for (Object obj : userList.toArray()) {
      User user = (User) obj;
      System.out.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
    }
  }

  private void viewUser() {
    int userNo = inputInt("회원번호?");

    User user = (User) userList.get(userList.indexOf(new User(userNo)));
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("연락처: %s\n", user.getTel());
  }

  private void updateUser() {
    int userNo = inputInt("회원번호?");
    User user = (User) userList.get(userList.indexOf(new User(userNo)));
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    user.setName(input(String.format("이름(%s)?", user.getName())));
    user.setEmail(input(String.format("이메일(%s)?", user.getEmail())));
    user.setPassword(input("암호?"));
    user.setTel(input(String.format("연락처(%s)?", user.getTel())));
    System.out.println("변경했습니다.");
  }

  private void deleteUser() {
    int userNo = inputInt("회원번호?");
    User deletedUser = (User) userList.get(userList.indexOf(new User(userNo)));
    if (deletedUser != null) {
      userList.remove(userList.indexOf(deletedUser));
      System.out.printf("'%s' 회원을 삭제 했습니다.", deletedUser.getName());
    } else {
      System.out.println("없는 회원입니다.");
    }
  }

  public LinkedList getUserList() {
    return this.userList;
  }

  @Override
  protected String[] getMenus() {
    return menus;
  }

}
