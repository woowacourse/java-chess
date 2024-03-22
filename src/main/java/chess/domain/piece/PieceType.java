package chess.domain.piece;

import static chess.domain.attribute.File.startBishopFileOf;
import static chess.domain.attribute.File.startKingFile;
import static chess.domain.attribute.File.startKnightFileOf;
import static chess.domain.attribute.File.startPawnFileOf;
import static chess.domain.attribute.File.startQueenFile;
import static chess.domain.attribute.File.startRookFileOf;
import static chess.domain.attribute.Rank.startPawnRankOf;
import static chess.domain.attribute.Rank.startRankOf;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.function.BiFunction;

public enum PieceType {
    KING(1, (color, index) -> Square.of(startKingFile(), startRankOf(color))),
    QUEEN(1, (color, index) -> Square.of(startQueenFile(), startRankOf(color))),
    BISHOP(2, (color, index) -> Square.of(startBishopFileOf(index), startRankOf(color))),
    KNIGHT(2, (color, index) -> Square.of(startKnightFileOf(index), startRankOf(color))),
    ROOK(2, (color, index) -> Square.of(startRookFileOf(index), startRankOf(color))),
    PAWN(8, (color, index) -> Square.of(startPawnFileOf(index), startPawnRankOf(color)));

    private final int count;
    private final BiFunction<Color, Integer, Square> startSquare;

    PieceType(final int count, final BiFunction<Color, Integer, Square> startSquare) {
        this.count = count;
        this.startSquare = startSquare;
    }

    public Square startSquareOf(final Color color, final int index) {
        return startSquare.apply(color, index);
    }

    public int getCount() {
        return count;
    }
}
