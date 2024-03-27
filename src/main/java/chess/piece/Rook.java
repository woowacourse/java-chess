package chess.piece;

import chess.position.UnitMovement;
import chess.score.PieceScore;
import java.util.Set;

public class Rook extends Piece {

    private static final int MAX_MOVE_STEP = 7;

    public Rook(Color color) {
        super(color,
                PieceScore.ROOK,
                Set.of(
                        UnitMovement.differencesOf(1, 0),
                        UnitMovement.differencesOf(0, 1),
                        UnitMovement.differencesOf(-1, 0),
                        UnitMovement.differencesOf(0, -1)
                )
        );
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_MOVE_STEP;
    }
}
