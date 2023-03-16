package chess.view;

import chess.domain.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceName {

    ROOK("R", PieceType.ROOK),
    KNIGHT("N", PieceType.KNIGHT),
    BISHOP("B", PieceType.BISHOP),
    QUEEN("Q", PieceType.QUEEN),
    KING("K", PieceType.KING),
    PAWN("P", PieceType.PAWN);

    private static final String WRONG_TYPE_ERROR_MESSAGE = "존재하지 않는 말 타입입니다.";

    private final String name;
    private final PieceType type;

    PieceName(final String name, final PieceType type) {
        this.name = name;
        this.type = type;
    }

    public static String findNameByPiece(final Piece piece) {
        PieceName foundName = Arrays.stream(values())
                .filter(pieceName -> pieceName.type == piece.getType())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_TYPE_ERROR_MESSAGE));
        return foundName.getName(piece.getColor());
    }

    public String getName(TeamColor color) {
        if (color == TeamColor.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

}
