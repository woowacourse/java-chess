package chess.view;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

public class PositionConvertor {

    public static Position convert(final String position) {
        int fileIndex = position.charAt(0) - 'a' + 1;
        int rankIndex = Character.getNumericValue(position.charAt(1));

        return new Position(File.of(fileIndex), Rank.of(rankIndex));
    }
}
