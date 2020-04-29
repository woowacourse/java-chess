package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;

public abstract class Piece {
    protected final Team team;

    protected Piece(Team team) {
        this.team = team;
    }

    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        CanNotMoveStrategy canNotMoveStrategy = constituteStrategy();
        return canNotMoveStrategy.canNotMove(from, to, piecesState);
    }

    protected abstract CanNotMoveStrategy constituteStrategy();

    boolean isSameTeam(Piece toPiece) {
        //todo: getter??
        return team.isSame(toPiece.team);
    }

    boolean isOppositeTeam(Piece toPiece) {
        return team.isOpposite(toPiece.team);
    }

}
