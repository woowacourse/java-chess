package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;

import java.util.List;

public abstract class Piece {

    private final String name;
    private final TeamType teamType;
    private final Score score;
    private final List<Direction> directions;

    protected Piece(String name, TeamType teamType, Score score, List<Direction> directions) {
        this.name = name;
        this.teamType = teamType;
        this.score = score;
        this.directions = directions;
    }

    public boolean isMovable(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        Direction direction = current.evaluateDirection(destination);
        if (!isCorrectDirection(direction)) {
            return false;
        }
        if (chessBoard.hasPieceOnRouteBeforeDestination(current, destination, direction)) {
            return false;
        }
        return chessBoard.isEmptyOrHasEnemyOn(destination, teamType);
    }

    protected boolean isCorrectDirection(Direction direction) {
        return directions.contains(direction);
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isTeamOf(TeamType teamType) {
        return this.teamType == teamType;
    }

    public String getName() {
        if (teamType == TeamType.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public double getScore() {
        return score.getScore();
    }
}
