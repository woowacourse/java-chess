package chess.view;

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

    public static void validateCommandIsMove(String input) {
        if (GameCommandView.isValidMoveCommand(input)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 입력 형식입니다.");
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
