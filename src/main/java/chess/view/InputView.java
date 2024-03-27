package chess.view;

import static chess.utils.Constant.END_COMMAND;
import static chess.utils.Constant.MOVE_COMMAND;
import static chess.utils.Constant.START_COMMAND;
import static chess.utils.Constant.STATUS_COMMAND;

import chess.domain.board.GameInformation;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String MOVE_COMMAND_FORMAT = "^" + MOVE_COMMAND + " [a-h][1-8] [a-h][1-8]$";
    private static final String DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readCommand() {
        String input = scanner.nextLine();
        if (input.equals(START_COMMAND) || input.equals(END_COMMAND) || input.equals(STATUS_COMMAND)) {
            return List.of(input);
        }
        if (isMoveCommand(input)) {
            return convertMoveCommand(input);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public int readGameId(List<GameInformation> gameInfos) {
        String input = scanner.nextLine();
        int gameId = convertToInt(input);
        if (isAppropriatedGameId(gameInfos, gameId)) {
            return gameId;
        }
        throw new IllegalArgumentException("존재하지 않는 게임 번호입니다.");
    }

    private boolean isMoveCommand(String input) {
        return input.matches(MOVE_COMMAND_FORMAT);
    }

    private List<String> convertMoveCommand(String input) {
        return Arrays.asList(input.split(DELIMITER));
    }

    private boolean isAppropriatedGameId(List<GameInformation> gameInfos, int gameId) {
        if (gameId == 0) {
            return true;
        }
        return gameInfos.stream().map(GameInformation::getGameId).toList().contains(gameId);
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("게임 번호는 숫자이어야합니다.");
        }
    }
}
