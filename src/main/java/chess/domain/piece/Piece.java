package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected static final int MIN_MOVEMENT = 1;

    protected final Character character;
    protected final boolean isMoved;

    protected Piece(Character character, boolean isMoved) {
        this.character = character;
        this.isMoved = isMoved;
    }

    public abstract Piece move();

    protected abstract boolean isMovable(int rowDifference, int columnDifference);

    public boolean isMovable(Movement movement, boolean isAttack) {
        return isMovable(movement.calculateRowDifference(), movement.calculateColumnDifference());
    }

    public boolean isMovable(Movement movement) {
        return isMovable(movement.calculateRowDifference(), movement.calculateColumnDifference());
    }

    public List<Position> findBetweenPositions(Movement movement, Boolean isAttack) {
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
        int rowDirection = findDirection(rowDifference);
        int columnDirection = findDirection(columnDifference);

        List<Position> positions = new ArrayList<>();
        for (int i = MIN_MOVEMENT; i < absoluteDifference; i++) {
            positions.add(position.move(rowDirection * i, columnDirection * i));
        }
        return positions;
    }

    private int findDirection(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }

    private void validateMovable(Movement movement) {
        if (isMovable(movement)) {
            return;
        }
        throw new ImpossibleMoveException("해당 위치로 움직일 수 없습니다.");
    }

    public boolean isSameTeamWith(Piece piece) {
        return isSameTeamWith(piece.character.team());
    }

    public boolean isSameTeamWith(Team team) {
        return this.character.team() == team;
    }

    public boolean isSameCharacter(Character character) {
        return this.character == character;
    }

    public Character character() {
        return character;
    }
}
