package chess.piece;

import chess.position.UnitDirection;
import chess.score.PieceScore;
import java.util.Set;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Bishop(Color color) {
        super(color,
                PieceScore.BISHOP,
                Set.of(
                        UnitDirection.differencesOf(1, 1),
                        UnitDirection.differencesOf(1, -1),
                        UnitDirection.differencesOf(-1, 1),
                        UnitDirection.differencesOf(-1, -1)
                )
        );
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_UNIT_MOVE;
    }
}
