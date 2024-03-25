package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
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

    public List<Position> findBetweenPositions(Movement movement) {
        List<Position> positions = new ArrayList<>();
        for (int i = MIN_MOVEMENT; i < movement.maxAbsoluteMoveDifference(); i++) {
            positions.add(movement.source().move(
                    movement.findRowDirection() * i, movement.findColumnDirection() * i));
        }
        return positions;
    }

    public boolean isSameTeamWith(Piece piece) {
        return isSameTeamWith(piece.character.team());
    }

    public boolean isSameTeamWith(Team team) {
        return this.character.team() == team;
    }

    public boolean isSameCharacter(Character character) {
        return this.character.equals(character);
    }

    public Character character() {
        return character;
    }
}
