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

    private static final int PAWN_SCORE = 1;

    public Pawn(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return PAWN_SCORE;
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
            return getPath(destination, direction);
        }
        Direction direction = findDirection(Direction.whitePawnDirection(isFirstTurn()), destination);
        return getPath(destination, direction);
    }

    private List<Position> getPath(Position destination, Direction direction) {
        List<Position> positions = position.getPathToDst(destination, direction);
        positions.add(destination);
        return positions;
    }

    private boolean isFirstTurn() {
        if (!isBlackTeam() && position.getRow() == Row.TWO) {
            return true;
        }
        return isBlackTeam() && position.getRow() == Row.SEVEN;
    }

    private Direction findDirection(List<Direction> directions, Position destination) {
        int colDiff = destination.getCol().getDifference(position.getCol());
        int rowDiff = destination.getRow().getDifference(position.getRow());

        return directions.stream()
                .filter(direction -> isMatch(colDiff, rowDiff, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    private boolean isMatch(int colDiff, int rowDiff, Direction direction) {
        return rowDiff == direction.getYDegree() && colDiff == direction.getXDegree();
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
