package chess.domain.piece;

import chess.Calculator;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.ArrayList;
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

    protected abstract boolean isMovable(int rowDifference, int columnDifference);

    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return isMovable(rowDifference, columnDifference);
    }

    public boolean isMovable(Movement movement) {
        return isMovable(movement.calculateRowDifference(), movement.calculateColumnDifference());
    }

    public List<Position> findBetweenPositionsWhenAttack(Movement movement) {
        return findBetweenPositions(movement);
    }

    public List<Position> findBetweenPositions(Movement movement) {
        validateMovable(movement);
        int rowDifference = movement.calculateRowDifference();
        int columnDifference = movement.calculateColumnDifference();

        return findBetweenPositions(movement.source(), rowDifference, columnDifference);
    }

    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        int absoluteDifference = Math.max(Math.abs(rowDifference), Math.abs(columnDifference));
        int rowSign = Calculator.calculateSign(rowDifference);
        int columnSign = Calculator.calculateSign(columnDifference);

        List<Position> positions = new ArrayList<>();
        for (int movement = MIN_MOVEMENT; movement < absoluteDifference; movement++) {
            positions.add(position.move(rowSign * movement, columnSign * movement));
        }
        return positions;
    }

    ;

    private void validateMovable(Movement movement) {
        if (isMovable(movement)) {
            return;
        }
        throw new ImpossibleMoveException("해당 위치로 움직일 수 없습니다.");
    }

    public boolean isAttacking(Movement movement) {
        return isAttackable(movement.calculateRowDifference(), movement.calculateColumnDifference());
    }

    public boolean isSameTeamWith(Piece piece) {
        return isSameTeamWith(piece.team);
    }

    public boolean isSameTeamWith(Team team) {
        return this.team == team;
    }
}
