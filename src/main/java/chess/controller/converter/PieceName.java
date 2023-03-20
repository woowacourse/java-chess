package chess.controller.converter;

import chess.domain.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceName {

    ROOK(PieceType.ROOK, "R"),
    KNIGHT(PieceType.KNIGHT, "N"),
    BISHOP(PieceType.BISHOP, "B"),
    QUEEN(PieceType.QUEEN, "Q"),
    KING(PieceType.KING, "K"),
    PAWN(PieceType.PAWN, "P");

    private static final String WRONG_TYPE_ERROR_MESSAGE = "존재하지 않는 말 타입입니다.";

    private final PieceType type;
    private final String name;

    PieceName(final PieceType type, final String name) {
        this.type = type;
        this.name = name;
    }

    public static String findNameByPiece(final Piece piece) {
        PieceName foundName = Arrays.stream(values())
                .filter(pieceName -> pieceName.type == piece.getType())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_TYPE_ERROR_MESSAGE));
        return foundName.getName(piece.getColor());
    }

    public String getName(Camp camp) {
        if (camp == Camp.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

}
