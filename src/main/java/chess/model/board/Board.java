package chess.model.board;

import chess.model.piece.PieceColor;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.position.Direction;
import chess.model.position.Distance;
import chess.model.position.Position;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> squares;

    private Board(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public static Board create() {
        return new Board(BoardFactory.create());
    }

    public void move(final Position source, final Position target, final PieceColor myColor) {
        validateMove(source, target, myColor);
        updateBoard(source, target);
    }

    private void validateMove(final Position source, final Position target, final PieceColor myColor) {
        validateSource(source, myColor);
        validatePieceMovable(source, target);
        validateWaypoint(source, target);
        validateTarget(target, myColor);
    }

    private void validateSource(final Position source, final PieceColor myColor) {
        if (isEmpty(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }

        if (isOtherTeam(source, myColor)) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private boolean isOtherTeam(final Position position, final PieceColor myColor) {
        return !isEmpty(position) && isDifferentColor(position, myColor);
    }

    private boolean isDifferentColor(final Position position, final PieceColor myColor) {
        final Piece piece = squares.get(position);

        return piece.isDifferentColor(myColor);
    }

    private void validateWaypoint(final Position source, final Position target) {
        final Distance distance = target.differ(source);
        final Direction direction = distance.findDirection();

        Position wayPoint = source.next(direction);
        while (!wayPoint.equals(target)) {
            if (isFull(wayPoint)) {
                throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다.");
            }

            wayPoint = wayPoint.next(direction);
        }
    }

    private void validateTarget(final Position target, final PieceColor myColor) {
        if (isFull(target) && isSameColor(target, myColor)) {
            throw new IllegalArgumentException("해당 좌표로 이동할 수 없습니다.");
        }
    }

    private boolean isFull(final Position target) {
        return !isEmpty(target);
    }

    private boolean isEmpty(final Position position) {
        final Piece piece = squares.get(position);

        return piece.isEmpty();
    }

    private boolean isSameColor(final Position target, final PieceColor myColor) {
        return !squares.get(target).isDifferentColor(myColor);
    }

    private void validatePieceMovable(final Position source, final Position target) {
        final Piece piece = squares.get(source);

        final Distance distance = target.differ(source);
        if (cannotMovePiece(target, piece, distance)) {
            throw new IllegalArgumentException("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
        }
    }

    private boolean cannotMovePiece(final Position target, final Piece piece, final Distance distance) {
        return !piece.isMovable(distance, squares.get(target).getColor());
    }

    private void updateBoard(final Position source, final Position target) {
        final Piece movePiece = squares.get(source);
        squares.put(target, movePiece.update());
        squares.put(source, Empty.getInstance());
    }

    public Map<Position, Piece> getSquares() {
        return Map.copyOf(squares);
    }
}
