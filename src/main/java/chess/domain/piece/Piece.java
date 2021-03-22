package chess.domain.piece;

import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.exception.ImpossibleMoveException;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final PieceDetails pieceDetails;
    private final AvailableDirections availableDirections;
    private Position currentPosition;
    private List<Position> movablePositions;
    private boolean moved;

    public Piece(PieceDetails pieceDetails, AvailableDirections availableDirections, Position position) {
        this(pieceDetails, availableDirections, new ArrayList<>(), position, false);
    }

    private Piece(PieceDetails pieceDetails, AvailableDirections availableDirections,
        List<Position> movablePositions, Position position, boolean moved) {
        this.pieceDetails = pieceDetails;
        this.availableDirections = availableDirections;
        this.movablePositions = movablePositions;
        this.currentPosition = position;
        this.moved = moved;
    }

    public void updateMovablePositions(List<Position> existPiecePositions,
        List<Position> enemiesPositions) {
        movablePositions = new ArrayList<>();
        movablePositions.addAll(
            availableDirections.movablePositions(existPiecePositions, currentPosition, pieceDetails.iterable()));
        movablePositions.addAll(
            availableDirections.killablePositions(enemiesPositions, currentPosition, pieceDetails.iterable()));
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
        return pieceDetails.isSameColor(teamColor);
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

    protected AvailableDirections directions() {
        return availableDirections;
    }

    public TeamColor enemyColor() {
        return pieceDetails.color().reverse();
    }

    public int column() {
        return currentPosition.column();
    }

    public Score score() {
        return pieceDetails.score();
    }

    public String name() {
        if (isSameColor(WHITE)) {
            return pieceDetails.name();
        }
        return pieceDetails.name().toUpperCase();
    }

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public boolean isNotSameColor(TeamColor currentColor) {
        return pieceDetails.isNotSameColor(currentColor);
    }
}
