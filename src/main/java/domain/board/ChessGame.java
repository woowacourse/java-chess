package domain.board;

import domain.piece.Color;
import domain.piece.move.Coordinate;
import domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public final class ChessGame {

    private static final Color PRIORITY_GIVEN_COLOR = Color.WHITE;

    private final Board board;
    private final Turn turn;

    public ChessGame() {
        this.board = new Board();
        this.turn = new Turn(PRIORITY_GIVEN_COLOR);
    }

    public boolean isGameNotOver() {
        return board.allKingAlive();
    }

    public Map<Color, Double> collectPoint() {
        Map<Color, Double> collectedPoints = new HashMap<>();
        collectedPoints.put(Color.BLACK, board.collectPointFor(Color.BLACK));
        collectedPoints.put(Color.WHITE, board.collectPointFor(Color.WHITE));
        return collectedPoints;
    }

    public void move(final Coordinate start, final Coordinate end) {
        validate(start, end);
        Piece findPiece = board.findSquare(start);
        board.replaceWithEmptySquare(start);
        board.replaceSquare(end, findPiece);
        turn.invert();
    }

    private void validate(final Coordinate start, final Coordinate end) {
        validateTurn(start);
        validateNotEmpty(start);
        validateMoveByRule(start, end);
        validateNotBlocked(start, end);
    }

    private void validateTurn(final Coordinate start) {
        Piece findPiece = board.findSquare(start);
        if (turn.isNotFor(findPiece.getColor())) {
            throw new IllegalArgumentException("[ERROR] 현재는 해당 팀의 턴이 아닙니다.");
        }
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

    private void validateNotBlocked(final Coordinate start, final Coordinate end) {
        Piece piece = board.findSquare(start);
        if (piece.canJump() || isNotBlockedWhenCantReap(start, end)) {
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
