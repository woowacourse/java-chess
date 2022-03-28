package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends JumpPiece {

    public Bishop(Team team, Position position) {
        super(team, position, 3);
    }

    @Override
    protected Direction findDirection(Position destination) {
        for (Direction direction : Direction.diagonalDirection()) {
            if (destination.getCol().getDifference(position.getCol()) * direction.getXDegree()
                    == (destination.getRow().getDifference(position.getRow()) * direction.getYDegree())
                    && (destination.getRow().getDifference(position.getRow()) * direction.getYDegree()) > 0) {
                return direction;
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    private boolean isMatch(int colDiff, int rowDiff, Direction direction) {
        return colDiff * direction.getXDegree() == rowDiff * direction.getYDegree()
                && rowDiff * direction.getYDegree() > 0;
    }
}
