package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {

    private static final int SOURCE = 0;
    private static final int TARGET = 1;
    private static final int ROW = 0;
    private static final int COLUMN = 1;

    private final double score;
    protected final Color color;

    public Piece(double score, Color color) {
        this.score = score;
        this.color = color;
    }

    public abstract boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board);

    public boolean isSameType(Class<? extends Piece> piece) {
        return this.getClass() == piece;
    }

    public static boolean isMovableDot(List<Direction> coordinatesOfMovable, Position source, Position target) {
        return coordinatesOfMovable
                .stream()
                .anyMatch(coordinate -> isTargetPosition(source, coordinate, target));
    }

    private static boolean isTargetPosition(Position source, Direction direction, Position target) {
        return source.findPossiblePosition(direction).isSamePosition(target);
    }

    public static boolean isMovableLine(List<Position> positions, List<Direction> move,
                                                 Map<Position, Piece> board) {
        Position source = positions.get(SOURCE);
        Position target = positions.get(TARGET);

        return move.stream()
                .anyMatch(moveUnit -> isMovablePositionByRecursion(source, target,
                        List.of(moveUnit.row(), moveUnit.column()), board));
    }

    private static boolean isMovablePositionByRecursion(Position source, Position target, List<Integer> moveUnit,
                                                  Map<Position, Piece> board) {
        int row = moveUnit.get(ROW);
        int column = moveUnit.get(COLUMN);
        if (source.isOverRange()) {
            return false;
        }
        if (source.isSamePosition(target)) {
            return !board.containsKey(source);
        }
        return isMovablePositionByRecursion(source.findPossiblePosition(row, column), target, moveUnit, board);
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isColor(Piece piece) {
        return this.color == piece.color;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }
}
