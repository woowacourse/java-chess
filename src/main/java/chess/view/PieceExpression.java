package chess.view;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;

public class PieceExpression {

    private static final Map<Piece, String> pieceExpression = Map.ofEntries(
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
            Map.entry(Pawn.of(WHITE), "p")
    );

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
