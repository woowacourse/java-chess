package view;

import java.util.Scanner;

public class InputView {

    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다";
    private static final String READ_COMMAND_MESSAGE = "게임 시작은 start, 종료는 end를 입력하세요.";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCommand() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(READ_COMMAND_MESSAGE);

        return scanner.nextLine();
    }
}
