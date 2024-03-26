package chess.view;

import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardExpression {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    public static String toExpression(final List<Piece> piece) {
        List<String> pieceExpressions = toPieceExpression(piece);
        StringBuilder stringBuilder = new StringBuilder();
        for (int width = 0; width < WIDTH; width++) {
            stringBuilder.append(makeLineExpression(width, pieceExpressions));
        }

        return stringBuilder.toString();
    }

    private static List<String> toPieceExpression(final List<Piece> piece) {
        return piece.stream()
                .map(PieceExpression::mapToExpression)
                .toList();
    }

    private static String makeLineExpression(final int lineNumber, final List<String> pieceExpressions) {
        int startIndex = lineNumber * WIDTH;
        int endIndex = lineNumber * WIDTH + HEIGHT;
        List<String> pieceExpressionsOnLine = pieceExpressions.subList(startIndex, endIndex);

        return pieceExpressionsOnLine.stream()
                .collect(Collectors.joining("", "", System.lineSeparator()));
    }
}
