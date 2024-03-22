package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INVALID_COMMAND_ERROR =
            "잘못된 명령어 입력입니다. 'end' 혹은 'move source 위치 target 위치'로 입력해주세요.";
    private static final String COMMAND_SEPARATOR = " ";
    private static final String CHESS_GAME_TITLE = String.format("> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3",
            GameCommand.START.value(), GameCommand.END.value(), GameCommand.MOVE.value(), GameCommand.MOVE.value());
    private static final String COMMAND_ERROR_MESSAGE = String.format("%s 또는 %s만 입력할 수 있습니다. 다시 입력하세요.",
            GameCommand.START.value(), GameCommand.END.value());

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
        if (!List.of(GameCommand.START.value(), GameCommand.END.value()).contains(command)) {
            throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
        }
    }

    public List<String> readCommand() {
        List<String> command = List.of(scanner.nextLine().split(COMMAND_SEPARATOR));

        if (!List.of(GameCommand.MOVE.value(), GameCommand.END.value()).contains(command.get(0))) {
            throw new IllegalArgumentException(INVALID_COMMAND_ERROR);
        }

        return command;
    }
}
