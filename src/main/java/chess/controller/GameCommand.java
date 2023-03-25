package chess.controller;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    INIT,
    START,
    MOVE,
    STATUS,
    END;

    public static final String INVALID_COMMAND_MESSAGE = "잘못된 커맨드를 입력하였습니다.";
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int SOURCE_AND_TARGET_LENGTH = 2;
    public static final int INPUT_SIZE = 3;
    private static final int DEFAULT_COMMAND_INDEX = 0;

    public static GameCommand from(final List<String> input) {
        GameCommand result = Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(input.get(DEFAULT_COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
        if (result == MOVE) {
            validateMoveCommand(input);
        }
        return result;
    }

    private static void validateMoveCommand(List<String> input) {
        if (input.size() != INPUT_SIZE) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
        if (input.get(SOURCE_INDEX).length() != SOURCE_AND_TARGET_LENGTH ||
                input.get(TARGET_INDEX).length() != SOURCE_AND_TARGET_LENGTH) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
    }

    public static Position getPosition(final List<String> input, final int index) {
        final String targetPosition = input.get(index);
        final int fileOrder = getFileOrder(targetPosition);
        final int rankOrder = getRankOrder(targetPosition);
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    private static int getFileOrder(final String command) {
        final int charToIntDifference = 96;
        return command.charAt(0) - charToIntDifference;
    }

    private static int getRankOrder(final String command) {
        final char charToIntDifference = '0';
        return command.charAt(1) - charToIntDifference;
    }

    public boolean isEnd() {
        return this == END;
    }
}
