package chess.domain.piece;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Team team, Position position) {
        super(team, Pawn.class.getSimpleName(), position, 1);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team, new Position(column, Row.find(row))));
        }
        return pawns;
    }

    @Override
    public List<Position> findPath(Position destination) throws IllegalArgumentException {
        if (isBlackTeam()) {
            Direction direction = findDirection(Direction.blackPawnDirection(isFirstTurn()), destination);
            return getPath(destination, direction, position.getCol(), position.getRow());
        }
        Direction direction = findDirection(Direction.whitePawnDirection(isFirstTurn()), destination);
        return getPath(destination, direction, position.getCol(), position.getRow());
    }

    private List<Position> getPath(Position destination, Direction direction, Column col, Row row) {
        List<Position> positions = new ArrayList<>();
        while (!(col == destination.getCol() && row == destination.getRow())) {
            col = col.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
            positions.add(new Position(col, row));
        }
        return positions;
    }

    private boolean isFirstTurn() {
        if (!isBlackTeam() && position.getRow() == Row.TWO) {
            return true;
        }
        return isBlackTeam() && position.getRow() == Row.SEVEN;
    }

    private Direction findDirection(List<Direction> directions, Position destination) {
        for (Direction direction : directions) {
            if (destination.getRow().getDifference(position.getRow()) == direction.getYDegree()
                    && destination.getCol().getDifference(position.getCol()) == direction.getXDegree()) {
                return direction;
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
