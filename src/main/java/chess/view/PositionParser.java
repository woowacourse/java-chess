package chess.view;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public final class PositionParser {

    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final char FIRST_ENG_CHAR = 'A';
    private static final char FIRST_NUMBER_CHAR = '1';
    private static final int INDEX_RANK_FILE_ADJUST = 1;

    public static Position parse(String source) {
        File file = File.from(charToFileIndex(source));
        Rank rank = Rank.from(charToRankIndex(source));

        return new Position(file, rank);
    }

    private static int charToFileIndex(final String source) {
        return source.charAt(FIRST_INDEX) - FIRST_ENG_CHAR + INDEX_RANK_FILE_ADJUST;
    }

    private static int charToRankIndex(final String source) {
        return source.charAt(SECOND_INDEX) - FIRST_NUMBER_CHAR + INDEX_RANK_FILE_ADJUST;
    }
}
