package chess.piece;

import chess.position.UnitDirection;
import chess.score.PieceScore;
import java.util.Set;

public class King extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public King(Color color) {
        super(color,
                PieceScore.KING,
                Set.of(
                        UnitDirection.differencesOf(1, 1),
                        UnitDirection.differencesOf(1, 0),
                        UnitDirection.differencesOf(1, -1),
                        UnitDirection.differencesOf(0, 1),
                        UnitDirection.differencesOf(0, -1),
                        UnitDirection.differencesOf(-1, 1),
                        UnitDirection.differencesOf(-1, 0),
                        UnitDirection.differencesOf(-1, -1)
                )
        );
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_UNIT_MOVE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
