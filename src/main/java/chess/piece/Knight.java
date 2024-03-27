package chess.piece;

import chess.position.UnitMovement;
import chess.score.PieceScore;
import java.util.Set;

public class Knight extends Piece {

    private static final int MAX_MOVE_STEP = 1;

    public Knight(Color color) {
        super(color,
                PieceScore.KNIGHT,
                Set.of(
                        UnitMovement.differencesOf(2, 1),
                        UnitMovement.differencesOf(2, -1),
                        UnitMovement.differencesOf(-2, 1),
                        UnitMovement.differencesOf(-2, -1),
                        UnitMovement.differencesOf(1, 2),
                        UnitMovement.differencesOf(1, -2),
                        UnitMovement.differencesOf(-1, 2),
                        UnitMovement.differencesOf(-1, -2)
                ));
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_MOVE_STEP;
    }
}
