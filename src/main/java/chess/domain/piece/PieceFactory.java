package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceFactory {
    BLACK_BISHOP("B", Position::valueOf, Bishop::from),
    BLACK_KING("K", Position::valueOf, King::from),
    BLACK_KNIGHT("N", Position::valueOf, Knight::from),
    BLACK_PAWN("P", Position::valueOf, Pawn::from),
    BLACK_QUEEN("Q", Position::valueOf, Queen::from),
    BLACK_ROOK("R", Position::valueOf, Rook::from),
    WHITE_BISHOP("b", Position::valueOf, Bishop::from),
    WHITE_KING("k", Position::valueOf, King::from),
    WHITE_KNIGHT("n", Position::valueOf, Knight::from),
    WHITE_PAWN("p", Position::valueOf, Pawn::from),
    WHITE_QUEEN("q", Position::valueOf, Queen::from),
    WHITE_ROOK("r", Position::valueOf, Rook::from);

    private final String pieceName;
    private final BiFunction<String, String, Position> positionBiFunction;
    private final BiFunction<String, Position, Piece> pieceBiFunction;

    PieceFactory(final String pieceName, final BiFunction<String, String, Position> positionBiFunction,
                 final BiFunction<String, Position, Piece> pieceBiFunction) {
        this.pieceName = pieceName;
        this.positionBiFunction = positionBiFunction;
        this.pieceBiFunction = pieceBiFunction;
    }

    public static Piece createByString(final String pieceString, final String positionString) {
        PieceFactory pieceFactory = Arrays.stream(PieceFactory.values())
                .filter(value -> value.getPieceName().equals(pieceString))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
        Position position = pieceFactory.getPositionBiFunction()
                .apply(positionString.substring(1, 2), positionString.substring(0, 1));
        return pieceFactory.getPieceBiFunction().apply(pieceString, position);
    }

    public String getPieceName() {
        return pieceName;
    }

    public BiFunction<String, String, Position> getPositionBiFunction() {
        return positionBiFunction;
    }

    public BiFunction<String, Position, Piece> getPieceBiFunction() {
        return pieceBiFunction;
    }
}
