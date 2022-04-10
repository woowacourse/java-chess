package chess.domain.game;

import chess.domain.pieces.Piece;
import chess.machine.Result;
import chess.domain.pieces.Color;
import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.*;

public final class ConsoleBoard {

    private static final double PAWN_PENALTY_SCORE = 0.5;
    private static final int KING_TOTAL_COUNT = 2;

    private final Map<Position, Piece> pieces;

    public ConsoleBoard(final Initializer initializer) {
        pieces = initializer.initialize();
    }

    public Optional<Piece> piece(final Position position) {
        if (pieces.containsKey(position)) {
            return Optional.of(pieces.get(position));
        }
        return Optional.empty();
    }

    public void move(final Position sourcePosition, final Position targetPosition) {
        final Piece piece = findPiece(sourcePosition);
        validateMovement(sourcePosition, targetPosition);
        pieces.remove(sourcePosition);
        pieces.put(targetPosition, piece);
    }

    private Piece findPiece(final Position sourcePosition) {
        final Optional<Piece> wrappedPiece = piece(sourcePosition);
        if (wrappedPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        return wrappedPiece.get();
    }

    public void validateMovement(Position sourcePosition, Position targetPosition) {
        validateNotEquals(sourcePosition, targetPosition);
        validateTargetNotSameColor(targetPosition, findPiece(sourcePosition));
        findPiece(sourcePosition).validateMovement(sourcePosition, targetPosition);
        checkPawnMovement(sourcePosition, targetPosition, findPiece(sourcePosition));
        validatePathEmpty(sourcePosition, targetPosition);
    }

    private void validateNotEquals(final Position sourcePosition, final Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    private void validateTargetNotSameColor(final Position targetPosition, final Piece piece) {
        if (pieces.containsKey(targetPosition) && piece.isSameColorPiece(findPiece(targetPosition))) {
            throw new IllegalArgumentException("목적지에 같은 색의 기물이 있으면 움직일 수 없습니다.");
        }
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

    public double calculateScore(final Color color) {
        return calculateDefaultScore(color) - countPawnsOnSameColumns(color) * PAWN_PENALTY_SCORE;
    }

    private double calculateDefaultScore(final Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private int countPawnsOnSameColumns(final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsOnSameColumn(column, color))
                .filter(count -> count > 1)
                .sum();
    }

    private int countPawnsOnSameColumn(final Column column, final Color color) {
        return (int) Arrays.stream(Row.values())
                .map(row -> piece(Position.valueOf(column, row)))
                .filter(piece -> piece.isPresent() && piece.get().isPawn() && piece.get().isSameColor(color))
                .count();
    }

    public Map<Result, Color> calculateFinalWinner() {
        if (isKingAlive(Color.WHITE) && isKingAlive(Color.BLACK)) {
            return calculateScoreWinner();
        }
        return calculateWinnerWithKing();
    }

    public Map<Result, Color> calculateScoreWinner() {
        Map<Result, Color> gameResult = new HashMap<>();
        if (calculateScore(Color.WHITE) > calculateScore(Color.BLACK)) {
            gameResult.put(Result.WIN, Color.WHITE);
        }
        if (calculateScore(Color.WHITE) < calculateScore(Color.BLACK)) {
            gameResult.put(Result.WIN, Color.BLACK);
        }
        return gameResult;
    }

    private Map<Result, Color> calculateWinnerWithKing() {
        Map<Result, Color> gameResult = new HashMap<>();
        if (isKingAlive(Color.WHITE)) {
            gameResult.put(Result.WIN, Color.WHITE);
        }
        if (isKingAlive(Color.BLACK)) {
            gameResult.put(Result.WIN, Color.BLACK);
        }
        return gameResult;
    }

    private boolean isKingAlive(final Color color) {
        return pieces.values()
                .stream()
                .anyMatch(piece -> piece.isKing() && piece.isSameColor(color));
    }
}
