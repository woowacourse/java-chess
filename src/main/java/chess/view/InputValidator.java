package chess.view;

import static chess.view.GameCommand.MOVE;
import static chess.view.GameCommand.START;

import java.util.List;

public class InputValidator {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int POSITION_COMMAND_LENGTH = 2;
    private static final char START_FILE = 'a';
    private static final char END_FILE = 'h';
    private static final char START_RANK = '1';
    private static final char END_RANK = '8';

    public static void validateBlank(List<String> commands) {
        if (commands.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력할 수 없습니다.");
        }
    }

    public static void validateStartCommand(String input) {
        if (!GameCommand.isStart(input)) {
            throw new IllegalArgumentException("[ERROR] 게임을 시작하기 위해서는 " + START.getCommand() + "를 입력해주세요.");
        }
    }

    public static void validateDigit(String input) {
        boolean isDigit = input.chars()
                .allMatch(Character::isDigit);
        if (!isDigit) {
            throw new IllegalArgumentException("[ERROR] 게임 아이디는 정수로 입력해주세요.");
        }
    }

    public static void validateMoveCommand(String input) {
        if (!GameCommand.isMove(input)) {
            throw new IllegalArgumentException("[ERROR] 게임을 진행하기 위해서는 " + MOVE.getCommand() + "를 입력해주세요.");
        }
    }

    public static void validateCommandSize(List<String> commands) {
        if (commands.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    public static void validateCommandLength(String sourceCommand, String targetCommand) {
        if (!(sourceCommand.length() == POSITION_COMMAND_LENGTH && targetCommand.length() == POSITION_COMMAND_LENGTH)) {
            throw new IllegalArgumentException("[ERROR] source위치와 target위치는 두 글자로 입력해주세요.");
        }
    }

    public static void validateFile(char file) {
        if (file < START_FILE || file > END_FILE) {
            throw new IllegalArgumentException("[ERROR] file을 " + START_FILE + "~" + END_FILE + "로 입력해주세요.");
        }
    }

    public static void validateRank(char rank) {
        if (rank < START_RANK || rank > END_RANK) {
            throw new IllegalArgumentException("[ERROR] rank를 " + START_RANK + "~" + END_RANK + "로 입력해주세요.");
        }
    }
}
