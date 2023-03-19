package chess.view;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class PositionConvertor {

    public static Position convert(final String position) {
        final int fileIndex = position.charAt(0) - 'a' + 1;
        final int rankIndex = Character.getNumericValue(position.charAt(1));

        return Position.of(File.from(fileIndex), Rank.of(rankIndex));
    }
}
