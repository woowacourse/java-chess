package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String CHESS_GAME_TITLE = String.format("> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3", START_COMMAND, END_COMMAND);
    private static final String COMMAND_ERROR_MESSAGE = String.format("%s 또는 %s만 입력할 수 있습니다. 다시 입력하세요.", START_COMMAND, END_COMMAND);

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readProgressCommand() {
        System.out.println(CHESS_GAME_TITLE);

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

    public List<String> readCommand() {
        List<String> command = List.of(scanner.nextLine().split(" "));

        if (!List.of("move", "end").contains(command.get(0))) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다. 'end' 혹은 'move source 위치 target 위치'로 입력해주세요.");
        }

        return command;
    }
}
