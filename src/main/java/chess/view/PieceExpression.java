package chess.view;

import chess.domain.piece.*;

import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class PieceExpression {

    private static final Map<Piece, String> PIECE_EXPRESSION = Map.ofEntries(
            Map.entry(King.of(BLACK), "K"),
            Map.entry(Queen.of(BLACK), "Q"),
            Map.entry(Rook.of(BLACK), "R"),
            Map.entry(Bishop.of(BLACK), "B"),
            Map.entry(Knight.of(BLACK), "N"),
            Map.entry(Pawn.of(BLACK), "P"),
            Map.entry(King.of(WHITE), "k"),
            Map.entry(Queen.of(WHITE), "q"),
            Map.entry(Rook.of(WHITE), "r"),
            Map.entry(Bishop.of(WHITE), "b"),
            Map.entry(Knight.of(WHITE), "n"),
            Map.entry(Pawn.of(WHITE), "p"),
            Map.entry(EmptyPiece.of(), ".")
    );

    public static String mapToExpression(final Piece piece) {
        String result = PIECE_EXPRESSION.get(piece);
        if (result == null) {
            throw new IllegalArgumentException("존재하지 않는 기물입니다.");
        }

        return result;
    }
}
