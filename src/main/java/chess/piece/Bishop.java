package chess.piece;

import chess.position.UnitMovement;
import chess.score.PieceScore;
import java.util.Set;

public class Bishop extends Piece {

    private static final int MAX_MOVE_STEP = 7;

    public Bishop(Color color) {
        super(color,
                PieceScore.BISHOP,
                Set.of(
                        UnitMovement.differencesOf(1, 1),
                        UnitMovement.differencesOf(1, -1),
                        UnitMovement.differencesOf(-1, 1),
                        UnitMovement.differencesOf(-1, -1)
                )
        );
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_MOVE_STEP;
    }
}
