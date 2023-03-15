package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Collections;
import java.util.Map;

public final class ChessBoardRank {
    private final Map<Position, Piece> rank;

    public ChessBoardRank(final Map<Position, Piece> rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "ChessBoardRank{" +
                "rank=" + rank +
                '}';
    }

    public Map<Position, Piece> getRank() {
        return Collections.unmodifiableMap(rank);
    }
}
