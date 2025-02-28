package bitcamp.myapp.command;

import bitcamp.myapp.util.List;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Board;

import java.util.Date;

import static bitcamp.myapp.util.Prompt.inputInt;


public class BoardCommand extends AbstractCommand {

  List boardList;
  private String[] menus = {"등록", "목록", "조회", "변경", "삭제"};

  public BoardCommand(String menuTitle, List boardList) {
    super(menuTitle);
    this.boardList = boardList;
  }


  @Override
  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
      case "등록":
        addBoard();
        break;
      case "조회":
        viewBoard();
        break;
      case "목록":
        listBoard();
        break;
      case "변경":
        updateBoard();
        break;
      case "삭제":
        deleteBoard();
        break;
    }
  }

  private void addBoard() {
    Board board = new Board();
    board.setTitle(Prompt.input("제목?"));
    board.setContent(Prompt.input("내용?"));
    board.setCreatedDate(new Date());
    board.setNo(Board.getNextSeqNo());
    boardList.add(board);
    System.out.println("등록했습니다.");
  }

  private void listBoard() {
    System.out.println("번호 제목 작성일 조회수");
    for (Object obj : boardList.toArray()) {
      Board board = (Board) obj;
      System.out.printf("%d %s %tY-%3$tm-%3$td %d\n", board.getNo(), board.getTitle(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

  private void viewBoard() {
    int boardNo = inputInt("게시글 번호?");
    Board board = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
    if (board == null) {
      System.out.println("없는 게시글입니다.");
      return;
    }
    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  private void updateBoard() {
    int boardNo = inputInt("게시글 번호?");
    Board board = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
    if (board == null) {
      System.out.println("없는 게시글입니다.");
      return;
    }
    board.setTitle(Prompt.input("제목(%s)?", board.getTitle()));
    board.setContent(Prompt.input("내용(%s)?", board.getContent()));
    System.out.println("변경했습니다.");
  }

  private void deleteBoard() {
    int boardNo = inputInt("게시글 번호?");
    Board deletedBoard = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
    if (deletedBoard != null) {
      boardList.remove(boardList.indexOf(deletedBoard));
      System.out.printf("%d번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());
    } else {
      System.out.println("없는 게시글입니다.");
    }
  }


  @Override
  protected String[] getMenus() {
    return menus;
  }


}
