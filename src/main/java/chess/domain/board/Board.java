package chess.domain.board;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceScore;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Board {
    private final Map<Position, AbstractPiece> value;

    public Board(Map<Position, AbstractPiece> value) {
        this.value = value;
    }

    public Optional<AbstractPiece> find(Position position) {
        return Optional.ofNullable(value.get(position));
    }

    public MoveResult executeCommand(Position from, Position to, PieceColor pieceColor) {
        AbstractPiece piece = value.get(from);

        if (Objects.isNull(piece)) {
            return MoveResult.EMPTY_CELL;
        }
        if (!piece.isPieceColor(pieceColor)) {
            return MoveResult.INVALID_TURN;
        }
        AbstractPiece otherPiece = value.get(to);

        if (!piece.isAbleToJump() && hasObstacle(from, to)) {
            return MoveResult.HAS_OBSTACLE;
        }

        if (otherPiece == null) {
            return move(from, to);
        }

        return attack(from, to);
    }

    private boolean hasObstacle(Position from, Position to) {
        if (from.isSameXAxis(to)) {
            return hasAnyPiece(from.getPositionsSameYAxisBetween(to));
        }

        if (from.isSameYAxis(to)) {
            return hasAnyPiece(from.getPositionsSameXAxisBetween(to));
        }

        if (from.isOnDiagonal(to)) {
            return hasAnyPiece(from.getPositionsSameDirectionDiagonalBetween(to));
        }

        return false;
    }

    private boolean hasAnyPiece(List<Position> positions) {
        return positions.stream()
                .map(value::get)
                .anyMatch(position -> !Objects.isNull(position));
    }

    private MoveResult move(Position from, Position to) {
        AbstractPiece piece = value.get(from);
        AbstractPiece otherPiece = value.get(to);

        if (!piece.isMovable(from, to)) {
            return MoveResult.INVALID_MOVE_STRATEGY;
        }

        if (isExistingSameTeam(piece, otherPiece)) {
            return MoveResult.EXISTING_SAME_TEAM;
        }

        value.put(to, piece);
        value.remove(from);
        return MoveResult.MOVE_SUCCESS;
    }

    private MoveResult attack(Position from, Position to) {
        AbstractPiece piece = value.get(from);
        AbstractPiece otherPiece = value.get(to);

        if (!piece.isAbleToAttack(from, to)) {
            return MoveResult.INVALID_MOVE_STRATEGY;
        }

        if (isExistingSameTeam(piece, otherPiece)) {
            return MoveResult.EXISTING_SAME_TEAM;
        }

        value.put(to, piece);
        value.remove(from);

        if (otherPiece.isPieceType(PieceScore.KING)) {
            return MoveResult.KILL_KING;
        }

        return MoveResult.KILL_ENEMY;
    }

    private boolean isExistingSameTeam(AbstractPiece piece, AbstractPiece otherPiece) {
        return !Objects.isNull(otherPiece) && otherPiece.isSameTeam(piece);
    }

    public double calculateScore(PieceColor pieceColor) {
        return value.values()
                .stream()
                .filter(piece -> piece.isPieceColor(pieceColor))
                .mapToDouble(AbstractPiece::getScore)
                .sum() - adjustPawnScore(pieceColor);
    }

    private double adjustPawnScore(PieceColor pieceColor) {
        int totalDuplicatedPawnCount = 0;

        for (XAxis xAxis : XAxis.values()) {
            totalDuplicatedPawnCount += getDuplicatedPawnCountInColumn(pieceColor, xAxis);
        }

        return totalDuplicatedPawnCount * 0.5;
    }

    private int getDuplicatedPawnCountInColumn(PieceColor pieceColor, XAxis xAxis) {
        List<Position> positions = Position.getPositionsByXAxis(xAxis);
        int count = countPawnByPositions(pieceColor, positions);

        if (count >= 2) {
            return count;
        }
        return 0;
    }

    private int countPawnByPositions(PieceColor pieceColor, List<Position> positions) {
        return (int) positions.stream()
                .map(value::get)
                .filter(piece -> !Objects.isNull(piece))
                .filter(piece -> piece.isPieceColor(pieceColor))
                .filter(piece -> piece.isPieceType(PieceScore.PAWN))
                .count();
    }
}
