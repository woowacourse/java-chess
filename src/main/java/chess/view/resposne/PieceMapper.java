package chess.view.resposne;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceMapper {
    WHITE_PAWN(PieceType.PAWN, Color.WHITE, "p"),
    WHITE_ROOK(PieceType.ROOK, Color.WHITE, "r"),
    WHITE_KNIGHT(PieceType.KNIGHT, Color.WHITE, "n"),
    WHITE_BISHOP(PieceType.BISHOP, Color.WHITE, "b"),
    WHITE_QUEEN(PieceType.QUEEN, Color.WHITE, "q"),
    WHITE_KING(PieceType.KING, Color.WHITE, "k"),
    EMPTY(PieceType.EMPTY, Color.NONE, "."),
    BLACK_PAWN(PieceType.PAWN, Color.BLACK, "P"),
    BLACK_ROOK(PieceType.ROOK, Color.BLACK, "R"),
    BLACK_KNIGHT(PieceType.KNIGHT, Color.BLACK, "N"),
    BLACK_BISHOP(PieceType.BISHOP, Color.BLACK, "B"),
    BLACK_QUEEN(PieceType.QUEEN, Color.BLACK, "Q"),
    BLACK_KING(PieceType.KING, Color.BLACK, "K");

    private final PieceType pieceType;
    private final Color color;
    private final String pieceName;

    PieceMapper(PieceType pieceType, Color color, String pieceName) {
        this.pieceType = pieceType;
        this.color = color;
        this.pieceName = pieceName;
    }

    public static String getPieceName(Piece piece) {
        return Arrays.stream(values())
                .filter(it -> it.pieceType == piece.getType())
                .filter(it -> it.color == piece.getColor())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"))
                .pieceName;
    }
}
