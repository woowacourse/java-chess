package chess.view;

import chess.domain.Position;

public class PositionConverter {

    private static final char RANK_SUBSTITUTION_VALUE = '0';
    private static final int FILE_SUBSTITUTION_VALUE = 'a' - 1;

    public static Position convertToSourcePosition(String command) {
        return new Position(convertRank(command.charAt(1)), convertFile(command.charAt(0)));
    }

    public static Position convertToTargetPosition(String command) {
        return new Position(convertRank(command.charAt(3)), convertFile(command.charAt(2)));
    }

    private static int convertRank(char rawRank) {
        return rawRank - RANK_SUBSTITUTION_VALUE;
    }

    private static int convertFile(char rawFile) {
        return rawFile - FILE_SUBSTITUTION_VALUE;
    }
}
