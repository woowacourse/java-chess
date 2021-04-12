package domain;

import domain.exception.ImmovableSamePositionException;
import domain.exception.PieceCannotMoveException;
import domain.exception.PieceEmptyException;
import domain.piece.objects.Empty;
import domain.piece.objects.Piece;
import domain.piece.objects.PieceFactory;
import domain.piece.position.Direction;
import domain.piece.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.piece.Color.BLACK;
import static domain.piece.Color.WHITE;
import static domain.piece.objects.Pawn.PAWNS;
import static domain.piece.position.Direction.diagonalDirection;
import static domain.piece.position.Direction.linearDirection;

public class Board {
    private Map<Position, Piece> pieceMap;

    public Board() {
        pieceMap = new HashMap<>(PieceFactory.createPieces());
    }

    public Board(Map<Position, Piece> pieces) {
        pieceMap = new HashMap<>(pieces);
    }

    public void move(Position start, Position end, boolean turn) {
        Piece piece = getPiece(start);
        if (piece.isEmpty()) {
            throw new PieceEmptyException();
        }

        if (start.equals(end)) {
            throw new ImmovableSamePositionException();
        }

        if ((!piece.existPath() && validPath(start, end, turn))
                || (piece.existPath() && validEndPosition(start, end, turn))) {
            pieceMap.remove(start);
            pieceMap.put(end, piece);
            return;
        }

        throw new PieceCannotMoveException(piece.name());
    }

    private boolean validEndPosition(Position start, Position end, boolean turn) {
        Piece piece = getPiece(start);
        piece.checkMovable(start, end, turn);

        if (piece.isPawn()) {
            Direction direction = piece.direction(start, end);
            return existEnemyWhenDiagonal(end, turn, direction) || notExistEnemyWhenLinear(start, end, direction);
        }

        return !getPiece(end).isSameColor(turn);
    }

    private boolean notExistEnemyWhenLinear(Position start, Position end, Direction direction) {
        if (!linearDirection().contains(direction)) {
            return false;
        }

        Position movePosition = start.move(direction);
        if (movePosition.equals(end) && getPiece(end).isEmpty()) {
            return true;
        }
        return movePosition.move(direction).equals(end) && PAWNS.containsKey(start) && getPiece(end).isEmpty();
    }

    private boolean existEnemyWhenDiagonal(Position end, boolean turn, Direction direction) {
        if (!diagonalDirection().contains(direction)) {
            return false;
        }
        Piece endPiece = getPiece(end);
        return !endPiece.isEmpty() && !endPiece.isSameColor(turn);
    }

    private boolean validPath(Position start, Position end, boolean turn) {
        Piece piece = getPiece(start);
        piece.checkMovable(start, end, turn);
        start = movePositionToEndPosition(start, end, piece);
        return start.equals(end) && !getPiece(end).isSameColor(turn);
    }

    private Position movePositionToEndPosition(Position start, Position end, Piece piece) {
        Direction direction = piece.direction(start, end);
        start = start.move(direction);
        while (!start.equals(end) && start.validPosition() && getPiece(start).isEmpty()) {
            start = start.move(direction);
        }
        return start;
    }

    public Map<Position, Piece> getPieceMap() {
        return Collections.unmodifiableMap(pieceMap);
    }

    public Piece getPiece(Position position) {
        return pieceMap.getOrDefault(position, Empty.create());
    }

    public Map<Position, Piece> getTeam(boolean color) {
        return pieceMap.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public Map<Position, Piece> getBlackTeam() {
        return getTeam(BLACK.getValue());
    }

    public Map<Position, Piece> getWhiteTeam() {
        return getTeam(WHITE.getValue());
    }

    public boolean isExistSamePawn(Map.Entry<Position, Piece> pawn) {
        Position position = pawn.getKey();
        return Direction.verticalDirection().stream()
                .map(direction -> position.move(direction))
                .filter(movePosition -> movePosition.validPosition() && !movePosition.equals(position))
                .map(movePosition -> getPiece(movePosition))
                .anyMatch(piece -> piece.isPawn() && piece.isSameColor(pawn.getValue()));
    }

    public boolean isKingDead(Piece endPiece) {
        return endPiece.isKing();
    }
}
