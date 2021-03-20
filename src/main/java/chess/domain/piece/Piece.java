package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
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
        if (!isCorrectDirection(moveCommandDirection)) {
            return false;
        }
        if (board.hasPieceOnRouteBeforeDestination(currentCoordinate, targetCoordinate,
            moveCommandDirection)) {
            return false;
        }
        return board.find(targetCoordinate).isMovable(teamType);
    }

    protected boolean isCorrectDirection(Direction moveCommandDirection) {
        return directions.contains(moveCommandDirection);
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

    public boolean isKing() {
        return name.equalsIgnoreCase("k");
    }
}
