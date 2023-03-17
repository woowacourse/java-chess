package view;

import java.util.Scanner;

public class InputView {

    private final Scanner SCANNER = new Scanner(System.in);

    public String requestUserInputDuringProgressChess() {
        return SCANNER.nextLine();
    }

    public void requestStartCommand() {
        if (GameCommand.from(SCANNER.nextLine()).equals(GameCommand.START)) {
            return;
        }
        throw new IllegalArgumentException("게임 시작 명령어를 입력해주세요.");
    }
}
