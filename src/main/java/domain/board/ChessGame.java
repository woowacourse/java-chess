package domain.board;

import domain.square.Square;
import domain.piece.Coordinate;

public final class ChessGame {

    private final Board board;

    public ChessGame() {
        this.board = new Board();
    }

    public void move(final Coordinate start, final Coordinate end) {
        validateMove(start, end);
        Square findSquare = board.findSquare(start);
        board.replaceWithEmptySquare(start);
        board.replaceSquare(end, findSquare);
    }

    private void validateMove(final Coordinate start, final Coordinate end) {
        validateMoveByRule(start, end);
        validateIsEmptySquareAt(end);
        validateNotBlocked(start, end);
    }

    private void validateMoveByRule(final Coordinate start, final Coordinate end) {
        if (board.isMovable(start, end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 방향으로 이동할 수 없습니다.");
    }

    private void validateIsEmptySquareAt(final Coordinate target) {
        if (board.isSquareEmptyAt(target)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 해당 위치에는 기물이 이미 존재합니다.");
    }

    private void validateNotBlocked(final Coordinate start, final Coordinate end) {
        Square square = board.findSquare(start);
        if (square.canReap() || isNotBlockedWhenCantReap(start, end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 해당 위치로 가는 길에 다른 기물이 존재합니다.");
    }

    private boolean isNotBlockedWhenCantReap(final Coordinate start, final Coordinate end) {
        Coordinate directionVector = DirectionVector.calculate(start, end);
        Coordinate indexCoordinate = start.add(directionVector);

        while (board.isSquareEmptyAt(indexCoordinate) &&
                !indexCoordinate.equals(end)) {
            indexCoordinate = indexCoordinate.add(directionVector);
        }
        return indexCoordinate.equals(end);
    }

    public Board getBoard() {
        return board;
    }
}
