package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.exception.ImpossibleMoveException;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.TeamColor.WHITE;

public abstract class Piece {

    private final String name;
    private final TeamColor teamColor;
    private final Score score;
    private final List<Direction> moveDirections;
    private final List<Direction> killDirections;
    private final boolean iterable;
    private Position currentPosition;
    private List<Position> movablePositions;
    private boolean moved;


    public Piece(String name, TeamColor teamColor, Score score, List<Direction> moveDirections,
                 List<Direction> killDirections, boolean iterable, Position position) {
        this.name = name;
        this.teamColor = teamColor;
        this.score = score;
        this.moveDirections = moveDirections;
        this.killDirections = killDirections;
        this.iterable = iterable;
        this.currentPosition = position;
        this.movablePositions = new ArrayList<>();
    }

    protected boolean notMoved() {
        return !moved;
    }

    public void addMovablePositions(List<Position> existPiecePositions, List<Position> enemiesPositions) {
        movablePositions = new ArrayList<>();
        for (Direction moveDirection : moveDirections) {
            addPosition(moveDirection, existPiecePositions);
        }

        for (Direction killDirection : killDirections) {
            addKillPosition(killDirection, enemiesPositions);
        }
    }

    private void addKillPosition(Direction killDirection, List<Position> enemiesPositions) {
        Position currentPosition = currentPosition();
        do {
            if (currentPosition.invalidGo(killDirection)) return;
            currentPosition = currentPosition.go(killDirection);

            if (enemiesPositions.contains(currentPosition)) {
                movablePositions.add(currentPosition);
                return;
            }
        } while (iterable);
    }

    protected void addPosition(Direction moveDirection, List<Position> existPiecePositions) {
        Position currentPosition = currentPosition();
        do {
            if (currentPosition.invalidGo(moveDirection)) return;
            currentPosition = currentPosition.go(moveDirection);

            if (existPiecePositions.contains(currentPosition)) return;

            movablePositions.add(currentPosition);
        } while (iterable);
    }

    public List<Position> movablePositions() {
        return movablePositions;
    }

    public boolean sameColor(TeamColor teamColor) {
        return this.teamColor == teamColor;
    }

    public boolean isSamePosition(Position position) {
        return currentPosition.equals(position);
    }

    public abstract boolean isPawn();

    public TeamColor teamColor() {
        return teamColor;
    }

    public Position currentPosition() {
        return currentPosition;
    }

    public String name() {
        if (teamColor == WHITE) {
            return name;
        }
        return name.toUpperCase();
    }

    public Score score() {
        return score;
    }

    public int row() {
        return currentPosition.row();
    }

    public void changePosition(Position targetPosition) throws ImpossibleMoveException {
        if (movablePositions.contains(targetPosition)) {
            currentPosition = targetPosition;
            moved = true;
            return;
        }
        throw new ImpossibleMoveException();
    }

    public TeamColor enemyColor() {
        return teamColor.reverse();
    }

    public boolean isNotSameColor(TeamColor currentColor) {
        return teamColor != currentColor;
    }

    public abstract boolean isKing();
}
