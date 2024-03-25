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
            GameStatus.START.value(), GameStatus.END.value(), GameStatus.MOVE.value(), GameStatus.MOVE.value());
    private static final String COMMAND_ERROR_MESSAGE = String.format("%s 또는 %s만 입력할 수 있습니다. 다시 입력하세요.",
            GameStatus.START.value(), GameStatus.END.value());
    private static final String IS_BLANK_ERROR = "잘못된 이동 입력입니다. 다시 입력하세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public UserCommand readProgressCommand() {
        System.out.println(CHESS_GAME_TITLE);

        GameStatus status = GameStatus.findByValue(scanner.nextLine());

        validateIsProgressCommand(status);

        return new UserCommand(status);
    }

    private void validateIsProgressCommand(GameStatus status) {
        if (!List.of(GameStatus.START, GameStatus.END).contains(status)) {
            throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
        }
    }

    public UserCommand readMoveCommand() {
        List<String> command = List.of(scanner.nextLine().split(COMMAND_SEPARATOR));

        GameStatus status = GameStatus.findByValue(command.get(0));
        validateIsEndOrMove(status);

        if (status.equals(GameStatus.END)) {
            return new UserCommand(status);
        }

        validateIsNotHavePosition(command);
        return new UserCommand(status, command.get(1), command.get(2));
    }

    private void validateIsEndOrMove(GameStatus status) {
        if (!List.of(GameStatus.MOVE, GameStatus.END).contains(status)) {
            throw new IllegalArgumentException(INVALID_COMMAND_ERROR);
        }
    }

    private void validateIsNotHavePosition(List<String> square) {
        if (square.size() != 3 || square.get(1).isBlank() || square.get(2).isBlank()) {
            throw new IllegalArgumentException(IS_BLANK_ERROR);
        }
    }
}
