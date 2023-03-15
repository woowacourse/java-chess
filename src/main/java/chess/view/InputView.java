package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return scanner.nextLine();
    }
}
