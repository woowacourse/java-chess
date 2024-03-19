package chess.view;

import chess.domain.Piece;

import java.util.Arrays;

import static chess.domain.PieceType.*;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public enum PieceExpression {

    BLACK_KING(Piece.of(KING, BLACK), "K"),
    BLACK_QUEEN(Piece.of(QUEEN, BLACK), "Q"),
    BLACK_ROOK(Piece.of(ROOK, BLACK), "R"),
    BLACK_BISHOP(Piece.of(BISHOP, BLACK), "B"),
    BLACK_KNIGHT(Piece.of(KNIGHT, BLACK), "N"),
    BLACK_PAWN(Piece.of(PAWN, BLACK), "P"),
    WHITE_KING(Piece.of(KING, WHITE), "k"),
    WHITE_QUEEN(Piece.of(QUEEN, WHITE), "q"),
    WHITE_ROOK(Piece.of(ROOK, WHITE), "r"),
    WHITE_BISHOP(Piece.of(BISHOP, WHITE), "b"),
    WHITE_KNIGHT(Piece.of(KNIGHT, WHITE), "n"),
    WHITE_PAWN(Piece.of(PAWN, WHITE), "p"),
    EMPTY(null, ".");

    private Piece piece;
    private String expression;

    PieceExpression(final Piece piece, final String expression) {
        this.piece = piece;
        this.expression = expression;
    }

    public static String mapToExpression(final Piece piece) {
        if (piece == null) {
            return EMPTY.expression;
        }

        return Arrays.stream(values())
                .filter(value -> value.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다."))
                .expression;
    }
}
