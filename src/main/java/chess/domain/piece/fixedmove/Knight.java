package chess.domain.piece.fixedmove;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Knight extends FixedMovePiece {
    public static final Piece WHITE_KNIGHT = new Knight(WHITE);
    public static final Piece BLACK_KNIGHT = new Knight(BLACK);

    private Knight(Team team) {
        super(PieceType.KNIGHT, team);
    }

    @Override
    Set<Entry<Integer, Integer>> weights() {
        return Set.of(
                Map.entry(-2, -1),
                Map.entry(-2, 1),
                Map.entry(-1, -2),
                Map.entry(-1, 2),
                Map.entry(1, -2),
                Map.entry(1, 2),
                Map.entry(2, -1),
                Map.entry(2, 1)
        );
    }
}
