package domain;

import domain.exception.ImmovableSamePositionException;
import domain.exception.InvalidTurnException;
import domain.exception.PieceCannotMoveException;
import domain.exception.PieceEmptyException;
import domain.piece.objects.Empty;
import domain.piece.objects.Piece;
import domain.piece.position.Direction;
import domain.piece.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.piece.Color.BLACK;
import static domain.piece.Color.WHITE;

public class Board {
    private Map<Position, Piece> board;

    public Board(Map<Position, Piece> pieces) {
        board = new HashMap<>(pieces);
    }

    public void move(Position start, Position end) {
        Piece piece = board.get(start);
        if (!piece.canMove(getBoard(), start, end)) {
            throw new PieceCannotMoveException(piece.getName());
        }
        board.remove(start);
        board.put(end, piece);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece getPiece(Position position) {
        return board.getOrDefault(position, Empty.create());
    }

    public void checkMovable(Position start, Position end, boolean color) {
        Piece piece = getPiece(start);
        checkInvalidTurn(color, piece);
        checkEmptyPiece(piece);
        checkSamePosition(start, end);
    }

    private void checkInvalidTurn(boolean color, Piece piece) {
        if (!piece.isSameColor(color)) {
            throw new InvalidTurnException();
        }
    }

    private void checkEmptyPiece(Piece piece) {
        if (piece.isEmpty()) {
            throw new PieceEmptyException();
        }
    }

    private void checkSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new ImmovableSamePositionException();
        }
    }

    public Map<Position, Piece> getTeam(boolean color) {
        return board.entrySet().stream()
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
                .filter(movePosition -> movePosition.notEmptyPosition() && !movePosition.equals(position))
                .map(movePosition -> getPiece(movePosition))
                .anyMatch(piece -> piece.isPawn() && piece.isSameColor(pawn.getValue()));
    }

    public boolean isKingDead(Piece endPiece) {
        return endPiece.isKing();
    }
}
