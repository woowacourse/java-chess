package chess.dto;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.piece.Bishop;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.None;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceMapper {

    PAWN("p", "P", Pawn::new),
    ROOK("r", "R", Rook::new),
    KNIGHT("n", "N", Knight::new),
    BISHOP("b", "B", Bishop::new),
    QUEEN("q", "Q", Queen::new),
    KING("k", "K", King::new),
    NONE(".", ".", color -> new None());

    private static final String WHITE_REGEXP = "^[a-z]$";
    private static final String BLACK_REGEXP = "^[A-Z]$";

    private final String whitePiece;
    private final String blackPiece;
    private final Function<Color, Piece> generate;

    PieceMapper(String whitePiece, String blackPiece, Function<Color, Piece> generate) {
        this.whitePiece = whitePiece;
        this.blackPiece = blackPiece;
        this.generate = generate;
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
        PieceMapper type = findType(pieceName);
        Color color = findColor(pieceName);
        return type.generate.apply(color);
    }

    private static PieceMapper findType(String pieceName) {
        return Arrays.stream(values())
            .filter(pieceMapper -> isSameType(pieceMapper, pieceName))
            .findFirst()
            .orElse(NONE);
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
