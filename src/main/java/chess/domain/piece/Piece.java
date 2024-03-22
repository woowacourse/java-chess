package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
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

    public boolean isMovable(Position sourcePosition, Position targetPosition) {
        int rowDifference = sourcePosition.calculateRowDifference(targetPosition);
        int columnDifference = sourcePosition.calculateColumnDifference(targetPosition);

        return isMovable(rowDifference, columnDifference);
    }

    public List<Position> findBetweenPositionsWhenAttack(Position sourcePosition, Position targetPosition) {
        return findBetweenPositions(sourcePosition, targetPosition);
    }

    public List<Position> findBetweenPositions(Position sourcePosition, Position targetPosition) {
        validateMovable(sourcePosition, targetPosition);
        int rowDifference = sourcePosition.calculateRowDifference(targetPosition);
        int columnDifference = sourcePosition.calculateColumnDifference(targetPosition);

        return findBetweenPositions(sourcePosition, rowDifference, columnDifference);
    }

    private void validateMovable(Position sourcePosition, Position targetPosition) {
        if (!sourcePosition.equals(targetPosition) && isMovable(sourcePosition, targetPosition)) {
            return;
        }
        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }

    public boolean isAttacking(Position sourcePosition, Position targetPosition) {
        int rowDifference = sourcePosition.calculateRowDifference(targetPosition);
        int columnDifference = sourcePosition.calculateColumnDifference(targetPosition);

        return !sourcePosition.equals(targetPosition) && isAttackable(rowDifference, columnDifference);
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
