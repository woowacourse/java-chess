package domain.board;

import domain.square.Color;
import domain.piece.move.Coordinate;
import domain.square.Square;

public final class ChessGame {

    private static final Color PRIORITY_GIVEN_COLOR = Color.WHITE;

    private final Board board;
    private final Turn turn;

    public ChessGame() {
        this.board = new Board();
        this.turn = new Turn(PRIORITY_GIVEN_COLOR);
    }

    // TODO: 테스트를 위한 생성자 유연성 증가
    public boolean isGameNotOver() {
        return board.allKingAlive();
    }

    public void move(final Coordinate start, final Coordinate end) {
        Square findSquare = board.findSquare(start);
        validateMove(start, end);
        validateTurn(findSquare);
        board.replaceWithEmptySquare(start);
        board.replaceSquare(end, findSquare);
        turn.invert();
    }

    private void validateTurn(final Square findSquare) {
        if (turn.isNotFor(findSquare.getColor())) {
            throw new IllegalArgumentException("[ERROR] 현재는 해당 팀의 턴이 아닙니다.");
        }
    }

    private void validateMove(final Coordinate start, final Coordinate end) {
        validateNotEmpty(start);
        validateMoveByRule(start, end);
        validateIsNotSameCamp(start, end);
        validateNotBlocked(start, end);
    }

    private void validateNotEmpty(final Coordinate start) {
        if (board.isSquareEmptyAt(start)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치에는 기물이 없습니다.");
        }
    }

    private void validateMoveByRule(final Coordinate start, final Coordinate end) {
        if (board.isMovable(start, end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 방향으로 이동할 수 없습니다.");
    }

    private void validateIsNotSameCamp(final Coordinate start, final Coordinate end) {
        if (board.isSameColor(start, end)) {
            throw new IllegalArgumentException("[ERROR] 같은 팀 기물은 잡을 수 없습니다.");
        }
    }

    private void validateNotBlocked(final Coordinate start, final Coordinate end) {
        Square square = board.findSquare(start);
        if (square.hasPieceCanJump() || isNotBlockedWhenCantReap(start, end)) {
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
