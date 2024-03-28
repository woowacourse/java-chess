package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import java.util.HashMap;
import java.util.Map;

public class EmptySquaresMaker {
    public static Map<Position, Square> make() {
        final Map<Position, Square> squares = new HashMap<>();
        for (Rank rank : Rank.values()) {
            squares.putAll(makeRank(rank));
        }
        return squares;
    }

    private static Map<Position, Square> makeRank(final Rank rank) {
        final Map<Position, Square> squares = new HashMap<>();
        for (File file : File.values()) {
            squares.put(new Position(rank, file), Empty.getInstance());
        }
        return squares;
    }
}
