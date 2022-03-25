package chess.domain;

public class Bishop extends Piece {

    public Bishop(Team team, Position position) {
        super(team, Bishop.class.getSimpleName(), position);
    }

    public void validateIsPossible(Position destination) {
        for (Direction direction : Direction.diagonalDirection()) {
            if (position.getCol().getDifference(destination.getCol()) / direction.getXDegree()
                    == (position.getRow().getDifference(destination.getRow()) / direction.getYDegree())) {
                return;
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }
}
