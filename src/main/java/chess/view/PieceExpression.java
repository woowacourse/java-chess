package chess.view;

import chess.domain.piece.Piece;
import java.util.Arrays;

public enum PieceExpression {

    KING("k"),
    QUEEN("q"),
    ROOK("r"),
    BISHOP("b"),
    KNIGHT("n"),
    PAWN("p"),
    EMPTY("."),
    ;

    private final String expression;

    PieceExpression(String expression) {
        this.expression = expression;
    }

    public static String mapToExpression(final Piece piece) {
        String foundExpression = findMatchedExpression(piece);

        if (piece.isBlack()) {
            return foundExpression.toUpperCase();
        }

        return foundExpression;
    }

    private static String findMatchedExpression(final Piece piece) {
        return Arrays.stream(values())
                .filter(expression -> expression.name()
                        .equals(piece.getOwnPieceTypeName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 유형의 기물입니다."))
                .expression;
    }
}
