package chess.domain.piece;

import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.exception.ImpossibleMoveException;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Details details;
    private final Directions directions;
    private Position currentPosition;
    private List<Position> movablePositions;
    private boolean moved;

    public Piece(Details details, Directions directions, Position position) {
        this(details, directions, new ArrayList<>(), position, false);
    }

    private Piece(Details details, Directions directions,
        List<Position> movablePositions, Position position, boolean moved) {
        this.details = details;
        this.directions = directions;
        this.movablePositions = movablePositions;
        this.currentPosition = position;
        this.moved = moved;
    }

    public void updateMovablePositions(List<Position> existPiecePositions,
        List<Position> enemiesPositions) {
        movablePositions = new ArrayList<>();
        movablePositions.addAll(
            directions.movablePositions(existPiecePositions, currentPosition, details.iterable()));
        movablePositions.addAll(
            directions.killablePositions(enemiesPositions, currentPosition, details.iterable()));
    }

    public boolean samePosition(Position position) {
        return currentPosition.equals(position);
    }

    public void move(Position targetPosition) {
        if (movablePositions.contains(targetPosition)) {
            currentPosition = targetPosition;
            moved = true;
            return;
        }
        throw new ImpossibleMoveException();
    }

    public boolean isSameColor(TeamColor teamColor) {
        return details.isSameColor(teamColor);
    }

    public Position currentPosition() {
        return currentPosition;
    }

    public List<Position> movablePositions() {
        return movablePositions;
    }

    protected boolean isNotMoved() {
        return !moved;
    }

    protected Directions directions() {
        return directions;
    }

    public TeamColor enemyColor() {
        return details.color().reverse();
    }

    public int column() {
        return currentPosition.column();
    }

    public Score score() {
        return details.score();
    }

    public String name() {
        if (isSameColor(WHITE)) {
            return details.name();
        }
        return details.name().toUpperCase();
    }

    public abstract boolean isKing();

    public abstract boolean isPawn() ;
}
