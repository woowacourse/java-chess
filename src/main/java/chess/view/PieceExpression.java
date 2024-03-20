package chess.view;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.PieceType.*;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public class PieceExpression {

    private static final Map<Piece, String> pieceExpression = new HashMap<>();

    static {
        pieceExpression.put(Piece.of(KING, BLACK), "K");
        pieceExpression.put(Piece.of(QUEEN, BLACK), "Q");
        pieceExpression.put(Piece.of(ROOK, BLACK), "R");
        pieceExpression.put(Piece.of(BISHOP, BLACK), "B");
        pieceExpression.put(Piece.of(KNIGHT, BLACK), "N");
        pieceExpression.put(Piece.of(PAWN, BLACK), "P");
        pieceExpression.put(Piece.of(KING, WHITE), "k");
        pieceExpression.put(Piece.of(QUEEN, WHITE), "q");
        pieceExpression.put(Piece.of(ROOK, WHITE), "r");
        pieceExpression.put(Piece.of(BISHOP, WHITE), "b");
        pieceExpression.put(Piece.of(KNIGHT, WHITE), "n");
        pieceExpression.put(Piece.of(PAWN, WHITE), "p");
    }

    public static String mapToExpression(final Piece piece) {
        if (piece == null) {
            return ".";
        }

        String result = pieceExpression.get(piece);
        if (result == null) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
        }

        return result;
    }
}
