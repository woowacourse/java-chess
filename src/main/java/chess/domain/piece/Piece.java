package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.List;

public abstract class Piece {
    protected static final int MIN_MOVEMENT = 1;

    protected final Team team;
    protected final boolean hasMoved;

    protected Piece(Team team, boolean hasMoved) {
        this.team = team;
        this.hasMoved = hasMoved;
    }

    public abstract Piece move();

    public abstract Character findCharacter();

    protected abstract List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference);

    protected abstract boolean isMovable(int rowDifference, int columnDifference);

    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return isMovable(rowDifference, columnDifference);
    }

    public boolean isMovable(Positions positions) {
        return isMovable(positions.calculateRowDifference(), positions.calculateColumnDifference());
    }

    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        return findBetweenPositions(positions);
    }

    public List<Position> findBetweenPositions(Positions positions) {
        validateMovable(positions);
        int rowDifference = positions.calculateRowDifference();
        int columnDifference = positions.calculateColumnDifference();

        return findBetweenPositions(positions.source(), rowDifference, columnDifference);
    }

    private void validateMovable(Positions positions) {
        if (isMovable(positions)) {
            return;
        }
        throw new ImpossibleMoveException("해당 위치로 움직일 수 없습니다.");
    }

    public boolean isAttacking(Positions positions) {
        return isAttackable(positions.calculateRowDifference(), positions.calculateColumnDifference());
    }

    public boolean isOppositeTeamWith(Team team) {
        return !isSameTeamWith(team);
    }

    public boolean isSameTeamWith(Piece piece) {
        return isSameTeamWith(piece.team);
    }

    public boolean isSameTeamWith(Team team) {
        return this.team == team;
    }
}
