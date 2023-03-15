package chess.domain.controller;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public final class PositionParser {

    public static Position parse(String source) {
        File file = File.from(source.charAt(0) - 'a' + 1);
        Rank rank = Rank.from(source.charAt(1) - '1' + 1);

        return new Position(file, rank);
    }
}
