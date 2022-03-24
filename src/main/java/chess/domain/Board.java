package chess.domain;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Optional;

public final class Board {

    private final Map<Position, Piece> pieces;

    public Board(final Initiator initiator) {
        pieces = initiator.initiate();
    }

    public Optional<Piece> piece(final Position position) {
        if (pieces.containsKey(position)) {
            return Optional.of(pieces.get(position));
        }
        return Optional.empty();
    }

    public void move(String source, String target) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        validateNotEquals(sourcePosition, targetPosition);
        Piece piece = findPiece(sourcePosition);
        validateTargetNotSameColor(targetPosition, piece);
        if (piece.isMovable(sourcePosition, targetPosition)) {
            checkPawnMovement(sourcePosition, targetPosition, piece);
            pieces.remove(sourcePosition);
            pieces.put(targetPosition, piece);
        }
    }

    private void validateTargetNotSameColor(Position targetPosition, Piece piece) {
        if (pieces.containsKey(targetPosition) && piece.isSameColor(findPiece(targetPosition))) {
            throw new IllegalArgumentException("[ERROR] 목적지에 같은 색의 기물이 있으면 움직일 수 없다.");
        }
    }

    private void checkPawnMovement(Position sourcePosition, Position targetPosition, Piece piece) {
        if (piece.isPawn() && Direction.calculate(sourcePosition, targetPosition) == Direction.DIAGONAL) {
            checkPawnTargetExist(targetPosition);
        }
    }

    private void checkPawnTargetExist(Position targetPosition) {
        if (!pieces.containsKey(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 상대기물이 목적지에 존재해야 대각선으로 움직일 수 있다.");
        }
    }

    private Piece findPiece(Position sourcePosition) {
        Optional<Piece> wrappedPiece = piece(sourcePosition);
        if (wrappedPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 말이 존재하지 않습니다.");
        }
        return wrappedPiece.get();
    }

    private void validateNotEquals(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발지와 목적지가 동일할 수 없습니다.");
        }
    }
}
