package chess.model.board;

import chess.model.piece.Direction;
import chess.model.piece.IndexConverter;
import chess.model.piece.PieceColor;
import chess.model.position.Distance;
import chess.model.position.Position;
import java.util.List;

public class Board {

    private static final int MINIMUM_TRY = 1;

    private final List<Square> squares;

    private Board(final List<Square> squares) {
        this.squares = squares;
    }

    public static Board create() {
        final List<Square> squares = BoardFactory.create();
        return new Board(squares);
    }

    public void move(final Position source, final Position target, final PieceColor pieceColor) {
        validateMove(source, target, pieceColor);

        final int sourceIndex = source.convertToIndex();
        final int targetIndex = target.convertToIndex();
        updateBoard(sourceIndex, targetIndex);
    }

    private void validateMove(final Position source, final Position target, final PieceColor pieceColor) {
        validateSource(source, pieceColor);
        validateWaypoint(source, target);
        validateTarget(target, pieceColor);
        validatePieceMovable(source, target, pieceColor);
    }

    private void validateSource(final Position source, final PieceColor pieceColor) {
        final int sourceIndex = source.convertToIndex();
        final Square sourceSquare = squares.get(sourceIndex);

        sourceSquare.validateExistence(pieceColor);
    }

    private void validateWaypoint(final Position source, final Position target) {
        final Distance distance = target.differ(source);
        final Direction direction = distance.findDirection();
        final int totalDistance = distance.convertToIndex();
        final int sourceIndex = source.convertToIndex();
        int count = IndexConverter.findCount(direction, totalDistance);

        checkWaypointSquare(direction, count, sourceIndex);
    }

    private void checkWaypointSquare(final Direction direction, int count, final int sourceIndex) {
        int nowIndex = IndexConverter.findNextIndex(direction, sourceIndex);

        while (count-- > MINIMUM_TRY) {
            final Square square = squares.get(nowIndex);

            square.validateWaypoint();
            nowIndex = IndexConverter.findNextIndex(direction, nowIndex);
        }
    }

    private void validateTarget(final Position target, final PieceColor pieceColor) {
        final int targetIndex = target.convertToIndex();
        final Square targetSquare = squares.get(targetIndex);

        targetSquare.validateEnemyPiece(pieceColor);
    }

    private void validatePieceMovable(final Position source, final Position target,
            final PieceColor pieceColor) {
        final Square sourceSquare = squares.get(source.convertToIndex());

        final Distance distance = target.differ(source);
        sourceSquare.validateMovable(distance);

        final int targetIndex = target.convertToIndex();
        if (sourceSquare.hasPawn()) {
            validatePawn(pieceColor, squares.get(targetIndex), distance.findDirection());
        }
    }

    private void updateBoard(final int sourceIndex, final int targetIndex) {
        final Square sourceSquare = squares.get(sourceIndex);

        final Square emptySquare = sourceSquare.removePiece();
        updateSquare(sourceIndex, emptySquare);

        final Square targetSquare = squares.get(targetIndex);
        final Square resultSquare = targetSquare.receivePiece(sourceSquare.pick());
        updateSquare(targetIndex, resultSquare);
    }

    private void validatePawn(final PieceColor pieceColor, final Square targetSquare,
            final Direction direction) {
        if (Direction.isDiagonal(direction)) {
            validatePawnAttack(pieceColor, targetSquare);
            return;
        }

        targetSquare.validateWaypoint();
    }

    private void validatePawnAttack(final PieceColor pieceColor, final Square targetSquare) {
        if (targetSquare.isEmpty() || targetSquare.isSameTeam(pieceColor)) {
            throw new IllegalArgumentException("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
        }
    }

    private void updateSquare(final int index, final Square square) {
        squares.remove(index);
        squares.add(index, square);
    }

    public List<Square> getSquares() {
        return List.copyOf(squares);
    }
}
