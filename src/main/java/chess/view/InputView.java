package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String CHESS_GAME_TITLE = "체스 게임을 시작합니다.";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String READ_COMMAND_MESSAGE = String.format("게임 시작은 %s, 종료는 %s 명령을 입력하세요.", START_COMMAND, END_COMMAND);
    private static final String COMMAND_ERROR_MESSAGE = String.format("%s 또는 %s만 입력할 수 있습니다. 다시 입력하세요.", START_COMMAND, END_COMMAND);

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readProgressCommand() {
        System.out.println(CHESS_GAME_TITLE);
        System.out.println(READ_COMMAND_MESSAGE);

        String command = scanner.nextLine();

        validateProgressCommand(command);

        return command;
    }

    private void validateProgressCommand(String command) {
        if (!List.of(START_COMMAND, END_COMMAND).contains(command)) {
            throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
        }
    }

    public boolean isStartCommand(String command) {
        return command.equals(START_COMMAND);
    }
}
