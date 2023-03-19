package chess.view;

import chess.domain.game.Position;

public class PositionConverter {

    private static final char RANK_SUBSTITUTION_VALUE = '0';
    private static final int FILE_SUBSTITUTION_VALUE = 'a' - 1;
    private static final int SOURCE_FILE_INDEX = 0;
    private static final int SOURCE_RANK_INDEX = 1;
    private static final int TARGET_FILE_INDEX = 2;
    private static final int TARGET_RANK_INDEX = 3;

    public static Position convertToSourcePosition(String command) {
        return new Position(
                convertRank(command.charAt(SOURCE_RANK_INDEX)),
                convertFile(command.charAt(SOURCE_FILE_INDEX))
        );
    }

    public static Position convertToTargetPosition(String command) {
        return new Position(
                convertRank(command.charAt(TARGET_RANK_INDEX)),
                convertFile(command.charAt(TARGET_FILE_INDEX))
        );
    }

    private static int convertRank(char rawRank) {
        return rawRank - RANK_SUBSTITUTION_VALUE;
    }

    private static int convertFile(char rawFile) {
        return rawFile - FILE_SUBSTITUTION_VALUE;
    }
}
