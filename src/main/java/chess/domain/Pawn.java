package chess.domain;

import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Team team, Position position) {
        super(team, Pawn.class.getSimpleName(), position);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team, new Position(column, Row.find(row))));
        }
        return pawns;
    }

    public void validateIsPossible(Position destination) throws IllegalArgumentException {
        if (isBlackTeam()) {
            validateIsPossiblePawnDestination(Direction.blackPawnDirection(isFirstTurn()), destination);
            return;
        }
        validateIsPossiblePawnDestination(Direction.whitePawnDirection(isFirstTurn()), destination);
    }

    private boolean isFirstTurn() {
        if (!isBlackTeam() && position.getRow() == Row.TWO) {
            return true;
        }
        return isBlackTeam() && position.getRow() == Row.SEVEN;
    }

    private void validateIsPossiblePawnDestination(List<Direction> directions, Position destination) {
        for (Direction direction : directions) {
            if (destination.getRow().getDifference(position.getRow()) == direction.getYDegree()
                    && destination.getCol().getDifference(position.getCol()) == direction.getXDegree()) {
                return;
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }
}
