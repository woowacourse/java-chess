package chess.domain.game;

import chess.domain.pieces.Piece;
import chess.domain.pieces.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.*;
import java.util.stream.Collectors;

public final class Board {

    private static final int KING_TOTAL_COUNT = 2;

    private final Map<Position, Piece> pieces;

    public Board(final Initializer initializer) {
        pieces = initializer.initialize();
    }

    public Optional<Piece> piece(final Position position) {
        if (pieces.containsKey(position)) {
            return Optional.of(pieces.get(position));
        }
        return Optional.empty();
    }

    public boolean move(final Position sourcePosition, final Position targetPosition) {
        final Piece piece = findPiece(sourcePosition);
        validateTargetNotSameColor(targetPosition, piece);

        return movePiece(sourcePosition, targetPosition, piece);
    }

    private Piece findPiece(final Position sourcePosition) {
        final Optional<Piece> wrappedPiece = piece(sourcePosition);
        if (wrappedPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        return wrappedPiece.get();
    }

    private void validateTargetNotSameColor(final Position targetPosition, final Piece piece) {
        if (pieces.containsKey(targetPosition) && piece.isSameColorPiece(findPiece(targetPosition))) {
            throw new IllegalArgumentException("목적지에 같은 색의 기물이 있으면 움직일 수 없습니다.");
        }
    }

    private boolean movePiece(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        final boolean movable = piece.isMovable(sourcePosition, targetPosition);
        if (movable) {
            checkPawnMovement(sourcePosition, targetPosition, piece);
            validatePathEmpty(sourcePosition, targetPosition);
            pieces.remove(sourcePosition);
            pieces.put(targetPosition, piece);
        }
        return movable;
    }

    private void validatePathEmpty(final Position source, final Position target) {
        final Direction direction = Direction.calculate(source, target);
        if (!direction.isIgnore()) {
            List<Position> positions = source.calculatePath(target, direction);
            validatePiecesNotExistOnPath(positions);
        }
    }

    private void validatePiecesNotExistOnPath(final List<Position> positions) {
        for (Position position : positions) {
            validatePieceNotExist(position);
        }
    }

    private void validatePieceNotExist(final Position position) {
        if (pieces.containsKey(position)) {
            throw new IllegalArgumentException("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
        }
    }

    private void checkPawnMovement(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        if (piece.isPawn() && Direction.calculate(sourcePosition, targetPosition).isDiagonal()) {
            checkPawnTargetExist(targetPosition);
        }
        if (piece.isPawn() && Direction.calculate(sourcePosition, targetPosition).isVertical()) {
            checkPawnTargetNotExist(targetPosition);
        }
    }

    private void checkPawnTargetExist(final Position targetPosition) {
        if (!pieces.containsKey(targetPosition)) {
            throw new IllegalArgumentException("폰은 상대기물이 목적지에 존재해야 대각선으로 움직일 수 있습니다.");
        }
    }

    private void checkPawnTargetNotExist(final Position targetPosition) {
        if (pieces.containsKey(targetPosition)) {
            throw new IllegalArgumentException("목적지에 다른 기물이 존재하면 움직일 수 없습니다.");
        }
    }

    public boolean isEnd() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count() != KING_TOTAL_COUNT;
    }

    public boolean isKingAlive(Color color) {
        return pieces.values()
                .stream()
                .anyMatch(piece -> piece.isKing() && piece.isSameColor(color));
    }

    public Collection<Piece> existPieces() {
        return pieces.values();
    }

    public Map<String, Piece> toMap() {
        return pieces.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey().toString(), Map.Entry::getValue));
    }
}
