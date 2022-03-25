package chess.domain;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
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

        movePiece(sourcePosition, targetPosition, piece);
    }

    private void movePiece(Position sourcePosition, Position targetPosition, Piece piece) {
        if (piece.isMovable(sourcePosition, targetPosition)) {
            checkPawnMovement(sourcePosition, targetPosition, piece);
            validatePathEmpty(sourcePosition, targetPosition);
            pieces.remove(sourcePosition);
            pieces.put(targetPosition, piece);
        }
    }

    private void validateTargetNotSameColor(Position targetPosition, Piece piece) {
        if (pieces.containsKey(targetPosition) && piece.isSameColorPiece(findPiece(targetPosition))) {
            throw new IllegalArgumentException("[ERROR] 목적지에 같은 색의 기물이 있으면 움직일 수 없다.");
        }
    }

    private void checkPawnMovement(Position sourcePosition, Position targetPosition, Piece piece) {
        if (piece.isPawn() && Direction.calculate(sourcePosition, targetPosition) == Direction.DIAGONAL) {
            checkPawnTargetExist(targetPosition);
        }
        if (piece.isPawn() && Direction.calculate(sourcePosition, targetPosition) == Direction.VERTICAL) {
            checkPawnTargetNotExist(targetPosition);
        }
    }

    private void checkPawnTargetNotExist(Position targetPosition) {
        if (pieces.containsKey(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 직진할 때 다른 기물이 존재하는 목적지에 이동할 수 없다.");
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

    private void validatePathEmpty(Position source, Position target) {
        Direction direction = Direction.calculate(source, target);
        if (direction.isIgnore()) {
            return;
        }
        List<Position> positions = source.calculatePath(target, direction);
        validatePiecesNotExistOnPath(positions);
    }

    private void validatePiecesNotExistOnPath(List<Position> positions) {
        for (Position position : positions) {
            validatePieceNotExist(position);
        }
    }

    private void validatePieceNotExist(Position position) {
        if (pieces.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
        }
    }

    public boolean isEnd() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count() != 2;
    }

    public double calculateScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(piece -> piece.score())
                .sum();
    }
}
