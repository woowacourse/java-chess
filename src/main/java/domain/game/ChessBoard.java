package domain.game;

import domain.movement.Direction;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceGenerator;
import domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> piecesPosition;

    public ChessBoard() {
        this.piecesPosition = PieceGenerator.generate();
    }

    public void checkRoute(Position source, Position target, Color color) {
        checkSourcePosition(source);
        checkTargetPosition(source, target);
        checkColor(source, color);

        Piece piece = piecesPosition.get(source);

        validatePieceMove(source, target);
        validateSlidingMove(source, target, piece);
        validatePawnMove(source, target, piece);
    }

    public void checkColor(Position source, Color color) {
        if (piecesPosition.get(source).isNotSameColor(color)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void checkSourcePosition(Position source) {
        if (isEmptyAt(source)) {
            throw new IllegalArgumentException("해당 위치에 Piece가 존재하지 않습니다.");
        }
    }

    private void checkTargetPosition(Position sourcePosition, Position targetPosition) {
        if (hasSameColorPiece(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (isSamePosition(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private boolean hasSameColorPiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = piecesPosition.get(sourcePosition);

        if (isNotEmptyAt(targetPosition)) {
            Piece targetPiece = piecesPosition.get(targetPosition);
            return sourcePiece.isSameColor(targetPiece);
        }
        return false;
    }

    private boolean isSamePosition(Position sourcePosition, Position targetPosition) {
        return sourcePosition.equals(targetPosition);
    }

    private void validatePieceMove(Position source, Position target) {
        Piece sourcePiece = piecesPosition.get(source);
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException("해당 피스가 움직일 수 있는 지점이 아닙니다.");
        }
    }

    private void validateSlidingMove(Position source, Position target, Piece piece) {
        if (piece.isSlidingPiece()) {
            validateOtherPieceOnRoute(source, target);
        }
    }

    private void validateOtherPieceOnRoute(Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        Position movePosition = sourcePosition.move(direction);

        while (!movePosition.equals(targetPosition)) {
            checkOtherPieceAt(movePosition);
            movePosition = movePosition.move(direction);
        }
    }

    private void checkOtherPieceAt(Position movePosition) {
        if (isNotEmptyAt(movePosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있으면 이동할 수 없습니다.");
        }
    }

    private void validatePawnMove(Position source, Position target, Piece piece) {
        if (piece.isPawn()) {
            validatePawnMove(source, target);
        }
    }

    private void validatePawnMove(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        if (direction.isNorthOrSouth()) {
            checkOtherPieceAt(target);
        }
        if (direction.isDiagonalDirection()) {
            checkEmptyAtDiagonal(target);
        }
    }

    private void checkEmptyAtDiagonal(Position target) {
        if (isEmptyAt(target)) {
            throw new IllegalArgumentException("이동하려는 곳에 기물이 없어 이동할 수 없습니다.");
        }
    }

    public void move(Position source, Position target) {
        Piece findPiece = piecesPosition.get(source);

        piecesPosition.remove(target);
        piecesPosition.put(target, findPiece);
        piecesPosition.remove(source);
    }

    public boolean isNotEmptyAt(Position position) {
        return piecesPosition.containsKey(position);
    }

    private boolean isEmptyAt(Position position) {
        return !isNotEmptyAt(position);
    }

    public Piece findPieceByPosition(Position targetPosition) {
        return piecesPosition.get(targetPosition);
    }

    public Map<Position, Piece> getPiecesPosition() {
        return Collections.unmodifiableMap(piecesPosition);
    }
}
