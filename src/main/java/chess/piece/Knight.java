package chess.piece;

import chess.position.UnitDirection;
import chess.score.PieceScore;
import java.util.Set;

public class Knight extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public Knight(Color color) {
        super(color,
                PieceScore.KNIGHT,
                Set.of(
                        UnitDirection.differencesOf(2, 1),
                        UnitDirection.differencesOf(2, -1),
                        UnitDirection.differencesOf(-2, 1),
                        UnitDirection.differencesOf(-2, -1),
                        UnitDirection.differencesOf(1, 2),
                        UnitDirection.differencesOf(1, -2),
                        UnitDirection.differencesOf(-1, 2),
                        UnitDirection.differencesOf(-1, -2)
                ));
    }

    @Override
    protected boolean isReachable(int step) {
        return step <= MAX_UNIT_MOVE;
    }
}
