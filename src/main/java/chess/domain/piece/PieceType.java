package chess.domain.piece;

import java.util.function.BiFunction;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;

public enum PieceType {
    KING(1, (color, index) -> Position.of(File.E, Rank.startRankOf(color))),
    QUEEN(1, (color, index) -> Position.of(File.D, Rank.startRankOf(color))),
    BISHOP(2, (color, index) -> {
        if (index == 0) {
            return Position.of(File.C, Rank.startRankOf(color));
        }
        return Position.of(File.F, Rank.startRankOf(color));
    }),
    KNIGHT(2, (color, index) -> {
        if (index == 0) {
            return Position.of(File.B, Rank.startRankOf(color));
        }
        return Position.of(File.G, Rank.startRankOf(color));
    }),
    ROOK(2, (color, index) -> {
        if (index == 0) {
            return Position.of(File.A, Rank.startRankOf(color));
        }
        return Position.of(File.H, Rank.startRankOf(color));
    }),
    PAWN(8, (color, index) -> Position.of(File.of(index), Rank.startPawnRankOf(color)))
    ;

    private final int count;
    private final BiFunction<Color, Integer, Position> startPosition;

    PieceType(final int count, final BiFunction<Color, Integer, Position> startPosition) {
        this.count = count;
        this.startPosition = startPosition;
    }

    public Position startPosition(final Color color, final int index) {
        return startPosition.apply(color, index);
    }

    public int getCount() {
        return count;
    }
}
