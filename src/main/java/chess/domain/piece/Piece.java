package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final TeamType teamType;
    private final String name;
    private final double score;
    private final List<Direction> directions;

    public Piece(TeamType teamType, String name, double score, List<Direction> directions) {
        this.teamType = teamType;
        this.name = name;
        this.score = score;
        this.directions = directions;
    }

    public boolean isMovableTo(Board board, Coordinate currentCoordinate,
        Coordinate targetCoordinate) {
        Direction moveCommandDirection = currentCoordinate.calculateDirection(targetCoordinate);
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        List<Direction> directions = getDirections();
        if (!directions.contains(moveCommandDirection)) {
            return false;
        }
        Coordinate movingCoordinate = currentCoordinate.move(moveCommandDirection);
        while (!movingCoordinate.equals(targetCoordinate)) {
            Cell cell = board.find(movingCoordinate);
            if (!cell.isEmpty()) {
                break;
            }
            possibleCoordinates.add(movingCoordinate);
            movingCoordinate = movingCoordinate.move(moveCommandDirection);
        }

        Cell cell = board.find(movingCoordinate);
        if (cell.isMovable(teamType)) {
            possibleCoordinates.add(movingCoordinate);
        }
        return possibleCoordinates.contains(targetCoordinate);
    }

    public boolean isPawn() {
        return name.equalsIgnoreCase("p");
    }

    public String getName() {
        if (teamType == TeamType.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public double getScore() {
        return score;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public boolean isTeamOf(TeamType teamType) {
        return this.teamType == teamType;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public boolean isKing() {
        return name.equalsIgnoreCase("k");
    }
}
