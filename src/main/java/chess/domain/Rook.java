package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Team team, Position position) {
        super(team, Rook.class.getSimpleName(), position);
    }

    public void findDirection(Position destination) {
        if (position.getRow().getDifference(destination.getRow()) == 0
                || position.getCol().getDifference(destination.getCol()) == 0) {
            return;
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    @Override
    public List<Position> findPath(Position destination) {
        return null;
    }
}
