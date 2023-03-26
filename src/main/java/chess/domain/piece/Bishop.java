package chess.domain.piece;

import chess.domain.MoveStrategy;
import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Piece pieceInTarget) {
        return MoveStrategy.BISHOP.isMovable(source, target)
                && !this.isSameTeam(pieceInTarget);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff);
    }

    @Override
    public PieceType type() {
        return PieceType.Bishop;
    }
}
