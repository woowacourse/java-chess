package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.List;

public abstract class Piece {
    protected final Team team;
    protected final boolean hasNotMoved;

    public Piece(Team team, boolean hasNotMoved) {
        this.team = team;
        this.hasNotMoved = hasNotMoved;
    }

    public abstract Character findCharacter();

    protected abstract boolean isRelativelyMovable(int rowDifference, int columnDifference);

    protected abstract List<Position> betweenPositions(Position position, int rowDifference, int columnDifference);

    private void validateMovable(Position oldPosition, Position newPosition) {
        int rowDifference = oldPosition.calculateRowDifference(newPosition);
        int columnDifference = oldPosition.calculateColumnDifference(newPosition);

        if (!oldPosition.equals(newPosition) && isRelativelyMovable(rowDifference, columnDifference)) {
            return;
        }

        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }

    public List<Position> betweenPositions(Position oldPosition, Position newPosition) {
        validateMovable(oldPosition, newPosition);
        int rowDifference = oldPosition.calculateRowDifference(newPosition);
        int columnDifference = oldPosition.calculateColumnDifference(newPosition);

        return betweenPositions(oldPosition, rowDifference, columnDifference);
    }
}
