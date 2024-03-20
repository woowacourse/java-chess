package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.Objects;

public abstract class Piece {
    protected final Position position;
    protected final Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract Character findCharacter();

    public Piece move(Position newPosition) {
        validateMovable(newPosition);
        return movedPiece(newPosition);
    }

    private void validateMovable(Position newPosition) {
        int differenceRow = position.differenceRow(newPosition);
        int differenceColumn = position.differenceColumn(newPosition);

        if (!position.equals(newPosition) && isMovable(differenceRow, differenceColumn)) {
            return;
        }

        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }

    protected abstract boolean isMovable(int differentRow, int differentColumn);

    protected abstract Piece movedPiece(Position newPosition);

    public Position position() {
        return position;
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
        return Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
