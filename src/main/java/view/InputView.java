package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String COMMAND_DELIMITER = " ";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public List<String> readCommand() {
        return List.of(scanner.nextLine().split(COMMAND_DELIMITER));
    }
}
