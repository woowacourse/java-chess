package domain.board;

import domain.piece.Color;
import domain.piece.move.Coordinate;
import domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessGame {

    private static final Color PRIORITY_GIVEN_COLOR = Color.WHITE;

    private final Board board;
    private final Turn turn;

    public ChessGame() {
        this(new Board());
    }

    public ChessGame(final Board board) {
        this.board = board;
        this.turn = new Turn(PRIORITY_GIVEN_COLOR);
    }

    public List<Color> getWinningColor() {
        Map<Color, Double> collectedPoints = collectPoint();
        double maxPoint = calculateMaxPoint(collectPoint());
        return getColorsWithMaxPoint(collectedPoints, maxPoint);
    }

    private double calculateMaxPoint(final Map<Color, Double> collectedPoints) {
        return collectedPoints.values().stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
    }

    private static List<Color> getColorsWithMaxPoint(
            final Map<Color, Double> collectedPoints,
            final double maxPoint
    ) {
        return collectedPoints.entrySet().stream()
                .filter(entry -> Double.compare(entry.getValue(), maxPoint) == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
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
        Piece findPiece = board.findPiece(start);
        board.replaceWithEmptyPiece(start);
        board.replacePiece(end, findPiece);
        turn.invert();
    }

    private void validate(final Coordinate start, final Coordinate end) {
        validateNotEmpty(start);
        validateTurn(start);
        validateSameColor(start, end);
        validateMoveByRule(start, end);
        validateNotBlocked(start, end);
    }

    private void validateTurn(final Coordinate start) {
        Piece findPiece = board.findPiece(start);
        if (turn.isNotFor(findPiece)) {
            throw new IllegalArgumentException("[ERROR] 현재는 해당 팀의 턴이 아닙니다.");
        }
    }

    private void validateNotEmpty(final Coordinate start) {
        if (board.isPieceEmptyAt(start)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치에는 기물이 없습니다.");
        }
    }

    private void validateSameColor(final Coordinate start, final Coordinate end) {
        if (board.findPiece(start).getColor() == board.findPiece(end).getColor()) {
            throw new IllegalArgumentException("[ERROR] 같은 팀이 있는 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateMoveByRule(final Coordinate start, final Coordinate end) {
        if (board.isMovable(start, end) || board.isAttackable(start, end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 선택한 기물은 해당 방향으로 이동할 수 없습니다.");
    }

    private void validateNotBlocked(final Coordinate start, final Coordinate end) {
        Piece piece = board.findPiece(start);
        if (piece.canJump() || isNotBlockedWhenCantReap(start, end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 해당 위치로 가는 길에 다른 기물이 존재합니다.");
    }

    private boolean isNotBlockedWhenCantReap(final Coordinate start, final Coordinate end) {
        Coordinate directionVector = DirectionVector.calculate(start, end);
        Coordinate indexCoordinate = start.add(directionVector);

        while (board.isPieceEmptyAt(indexCoordinate) &&
                !indexCoordinate.equals(end)) {
            indexCoordinate = indexCoordinate.add(directionVector);
        }
        return indexCoordinate.equals(end);
    }

    public Board getBoard() {
        return board;
    }
}
