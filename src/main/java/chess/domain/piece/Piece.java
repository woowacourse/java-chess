package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;

public abstract class Piece {
    protected final Team team;
    protected final boolean hasNotMoved;

    public Piece(Team team, boolean hasNotMoved) {
        this.team = team;
        this.hasNotMoved = hasNotMoved;
    }

    public abstract Character findCharacter();

    public void validateMovable(Position oldPosition, Position newPosition) {
        int differenceRow = oldPosition.differenceRow(newPosition);
        int differenceColumn = oldPosition.differenceColumn(newPosition);

        if (!oldPosition.equals(newPosition) && isRelativelyMovable(differenceRow, differenceColumn)) {
            return;
        }

        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }

    protected abstract boolean isRelativelyMovable(int differentRow, int differentColumn);
}
