package chess.dto;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.piece.Piece;
import java.util.Arrays;

public enum PieceMapper {

    PAWN("p", "P"),
    ROOK("r", "R"),
    KNIGHT("n", "N"),
    BISHOP("b", "B"),
    QUEEN("q", "Q"),
    KING("k", "K"),
    NONE(".", ".");

    private static final String WHITE_REGEXP = "^[a-z]$";
    private static final String BLACK_REGEXP = "^[A-Z]$";

    private final String whitePiece;
    private final String blackPiece;

    PieceMapper(String whitePiece, String blackPiece) {
        this.whitePiece = whitePiece;
        this.blackPiece = blackPiece;
    }

    public static String serialize(Piece piece) {
        PieceMapper pieceType = findPieceType(piece);
        Color color = Color.findColor(piece);
        if (color.isWhite()) {
            return pieceType.whitePiece;
        }
        return pieceType.blackPiece;
    }

    private static PieceMapper findPieceType(Piece piece) {
        Type type = Type.findType(piece);
        return Arrays.stream(values())
            .filter(pieceMapper -> pieceMapper.name().equals(type.name()))
            .findFirst()
            .orElse(NONE);
    }

    public static Piece deserialize(String pieceName) {
        Type type = findType(pieceName);
        Color color = findColor(pieceName);
        return Piece.of(type, color);
    }

    public static Type findType(String pieceName) {
        String type = Arrays.stream(values())
            .filter(pieceMapper -> isSameType(pieceMapper, pieceName))
            .findFirst()
            .orElse(NONE)
            .name();
        return Type.findType(type);
    }

    private static boolean isSameType(PieceMapper mapper, String pieceName) {
        return mapper.whitePiece.equals(pieceName) || mapper.blackPiece.equals(pieceName);
    }

    public static Color findColor(String pieceName) {
        if (pieceName.matches(WHITE_REGEXP)) {
            return Color.WHITE;
        }
        if (pieceName.matches(BLACK_REGEXP)) {
            return Color.BLACK;
        }
        return Color.NONE;
    }
}
