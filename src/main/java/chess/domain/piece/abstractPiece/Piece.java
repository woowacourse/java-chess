package chess.domain.piece.abstractPiece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.List;
import java.util.Objects;

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

    public abstract boolean isMovable(Movement movement, boolean isAttack);

    public abstract List<Position> findBetweenPositions(Movement movement);

    public boolean isMovable(Movement movement) {
        return isMovable(movement.calculateRowDifference(), movement.calculateColumnDifference());
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

    public Kind kind() {
        return character.kind();
    }

    public Team team() {
        return character.team();
    }

    public double point() {
        return character.kind().point();
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return isMoved == piece.isMoved && Objects.equals(character, piece.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, isMoved);
    }
}
