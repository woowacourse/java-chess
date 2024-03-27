package chess.piece;

import chess.position.UnitDirection;
import chess.score.PieceScore;
import java.util.Set;

public class Queen extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Queen(Color color) {
        super(color,
                PieceScore.QUEEN,
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
}
