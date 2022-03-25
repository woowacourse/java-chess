package chess.domain;

import java.util.List;

public class Queen extends Piece {

    public Queen(Team team, Position position) {
        super(team, Queen.class.getSimpleName(), position);
    }

    public void validateIsPossible(Position destination) {
        if (position.getRow().getDifference(destination.getRow()) == 0
                || position.getCol().getDifference(destination.getCol()) == 0) {
            return;
        }
        for (Direction direction : Direction.diagonalDirection()) {
            if (position.getCol().getDifference(destination.getCol()) / direction.getXDegree()
                    == (position.getRow().getDifference(destination.getRow()) / direction.getYDegree())) {
                return;
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    @Override
    public List<Position> findPath(Position destination) {
        return null;
    }
}