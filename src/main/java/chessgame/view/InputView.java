package chessgame.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public int readBoardNo(List<Integer> runningBoards) {
        System.out.println("실행할 체스 게임을 입력하세요");
        System.out.println("0 (새 게임)");
        runningBoards.forEach(System.out::println);
        return getAnInt(runningBoards);
    }

    private int getAnInt(List<Integer> runningBoards) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력해주세요");
            return readBoardNo(runningBoards);
        }
    }
}
