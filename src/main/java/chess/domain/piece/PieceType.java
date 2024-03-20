package chess.domain.piece;

import static chess.domain.attribute.File.startBishopFileOf;
import static chess.domain.attribute.File.startKingFile;
import static chess.domain.attribute.File.startKnightFileOf;
import static chess.domain.attribute.File.startPawnFileOf;
import static chess.domain.attribute.File.startQueenFile;
import static chess.domain.attribute.File.startRookFileOf;
import static chess.domain.attribute.Rank.startPawnRankOf;
import static chess.domain.attribute.Rank.startRankOf;

import java.util.function.BiFunction;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public enum PieceType {
    KING(1, (color, index) -> Position.of(startKingFile(), startRankOf(color))),
    QUEEN(1, (color, index) -> Position.of(startQueenFile(), startRankOf(color))),
    BISHOP(2, (color, index) -> Position.of(startBishopFileOf(index), startRankOf(color))),
    KNIGHT(2, (color, index) -> Position.of(startKnightFileOf(index), startRankOf(color))),
    ROOK(2, (color, index) -> Position.of(startRookFileOf(index), startRankOf(color))),
    PAWN(8, (color, index) -> Position.of(startPawnFileOf(index), startPawnRankOf(color)));

    private final int count;
    private final BiFunction<Color, Integer, Position> startPosition;

    PieceType(final int count, final BiFunction<Color, Integer, Position> startPosition) {
        this.count = count;
        this.startPosition = startPosition;
    }

    public Position startPositionOf(final Color color, final int index) {
        return startPosition.apply(color, index);
    }

    public int getCount() {
        return count;
    }
}
