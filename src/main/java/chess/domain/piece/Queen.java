package chess.domain.piece;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Team team, Position position) {
        super(team, Queen.class.getSimpleName(), position, 9);
    }

    private Direction findDirection(Position destination) {
        for (Direction direction : Direction.everyDirection()) {
            for (int i = 1; i <= 8; i++) {
                if (destination.getRow().getDifference(position.getRow()) == direction.getYDegree() * i
                        && destination.getCol().getDifference(position.getCol()) == direction.getXDegree() * i) {
                    return direction;
                }
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    @Override
    public List<Position> findPath(Position destination) {
        Direction direction = findDirection(destination);
        return getPath(destination, direction,
                position.getCol().plusColumn(direction.getXDegree()),
                position.getRow().plusRow(direction.getYDegree()));
    }

    private List<Position> getPath(Position destination, Direction direction, Column col, Row row) {
        List<Position> positions = new ArrayList<>();
        while(!(col == destination.getCol() && row == destination.getRow())) {
            positions.add(new Position(col, row));
            col = col.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
        }
        return positions;
    }
}