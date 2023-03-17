package chess.controller;

import java.util.List;

public enum GameCommand {
    MOVE,
    END;

    public static GameCommand of(String input) {
        if ("move".equalsIgnoreCase(input)) {
            return MOVE;
        }
        if ("end".equalsIgnoreCase(input)) {
            return END;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }

    public static void validate(List<String> input) {
        if (GameCommand.of(input.get(0)) == MOVE) {
            validateMoveCommand(input);
        }
        if (GameCommand.of(input.get(0)) == END) {
            validateEndCommand(input);
        }
    }

    private static void validateMoveCommand(List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("move 명령어는 '도착지'와 '출발지'에 대한 정보를 입력해야합니다.");
        }
    }

    private static void validateEndCommand(List<String> input) {
        if (input.size() != 1) {
            throw new IllegalArgumentException("end 명령어는 값을 하나만 입력해야합니다.");
        }
    }

}
