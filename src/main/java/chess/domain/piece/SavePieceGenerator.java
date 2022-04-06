package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.piece.mulitiplemovepiece.Queen;
import chess.domain.piece.mulitiplemovepiece.Rook;
import chess.domain.piece.unitmovepiece.King;
import chess.domain.piece.unitmovepiece.Knight;

public enum SavePieceGenerator {
    KING((type) -> new King(Color.NONE).isSameName(type), color -> new King(Color.getColor(color))),
    QUEEN((type) -> new Queen(Color.NONE).isSameName(type), color -> new Queen(Color.getColor(color))),
    ROOK((type) -> new Rook(Color.NONE).isSameName(type), color -> new Rook(Color.getColor(color))),
    BISHOP((type) -> new Bishop(Color.NONE).isSameName(type), color -> new Bishop(Color.getColor(color))),
    KNIGHT((type) -> new Knight(Color.NONE).isSameName(type), color -> new Knight(Color.getColor(color))),
    PAWN((type) -> new Pawn(Color.NONE).isSameName(type), color -> new Pawn(Color.getColor(color)));

    private final Predicate<String> condition;
    private final Function<String, Piece> of;

    SavePieceGenerator(Predicate<String> condition,
        Function<String, Piece> of) {
        this.condition = condition;
        this.of = of;
    }

    public static Piece generatePiece(String type, String color) {
        return Arrays.stream(SavePieceGenerator.values())
            .filter(piece -> piece.condition.test(type))
            .map(piece -> piece.of.apply(color))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
