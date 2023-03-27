package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.role.Bishop;
import chess.domain.piece.role.King;
import chess.domain.piece.role.Knight;
import chess.domain.piece.role.Pawn;
import chess.domain.piece.role.Queen;
import chess.domain.piece.role.Rook;
import java.util.function.BiFunction;

public enum Type {
    KING("King", King::new),
    QUEEN("Queen", Queen::new),
    BISHOP("Bishop", Bishop::new),
    ROOK("Rook", Rook::new),
    KNIGHT("Knight", Knight::new),
    PAWN("Pawn", Pawn::new);

    private final String label;
    private final BiFunction<Color, PiecePosition, Piece> pieceConstructor;

    Type(final String label, final BiFunction<Color, PiecePosition, Piece> pieceConstructor) {
        this.label = label;
        this.pieceConstructor = pieceConstructor;
    }

    public String label() {
        return label;
    }

    public BiFunction<Color, PiecePosition, Piece> getPieceConstructor() {
        return pieceConstructor;
    }

    public static Type ofString(String input) {
        return Type.valueOf(input.toUpperCase());
    }

}
