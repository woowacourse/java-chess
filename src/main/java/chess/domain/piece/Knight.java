package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public class Knight extends Piece {
    private List<Direction> directions = List.of(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW, Direction.EEN, Direction.EES, Direction.WWN, Direction.WWS);
    public Knight(Team team, Position position) {
        super(team, "N", position, 2.5);
    }

    private Direction findDirection(Position destination) {
        int colDifference = destination.getColDifference(position.getCol());
        int rowDifference = destination.getRowDifference(position.getRow());
        return Direction.find(rowDifference, colDifference, directions);
    }

    @Override
    public List<Position> findPath(Position destination) {
        findDirection(destination);
        return List.of();
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}