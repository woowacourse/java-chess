package chess.domain;

import java.util.List;

public class Knight extends Piece {

    public Knight(Team team, Position position) {
        super(team, Knight.class.getSimpleName(), position);
    }

    public void validateIsPossible(Position destination) {
        for (Direction direction : Direction.knightDirection()) {
            if (position.getRow().getDifference(destination.getRow()) == direction.getYDegree()
            && position.getCol().getDifference(destination.getCol()) == direction.getXDegree()) {
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