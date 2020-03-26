package chess.board.piece;

import chess.board.MoveInfo;
import chess.board.Vector;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(final Team team) {
        super(team, KNIGHT_SCORE);
    }

    @Override
    public boolean canMove(final MoveInfo moveInfo, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return moveInfo.sumOfAbsolute() == 3 && moveInfo.subtractOfAbsolute() == 1;
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        return Collections.emptyList();
    }
}
