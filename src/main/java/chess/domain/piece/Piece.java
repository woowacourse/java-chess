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

    private final Type type;
    protected Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board);

    public static boolean isMovableDot(List<Direction> coordinatesOfMovable, Position source, Position target) {
        return coordinatesOfMovable
                .stream()
                .anyMatch(coordinate -> isTargetPosition(source, coordinate, target));
    }

    private static boolean isTargetPosition(Position source, Direction direction, Position target) {
        return source.findPossiblePosition(direction).isSamePosition(target);
    }

    public static boolean isMovableLine(List<Position> positions, List<List<Integer>> move,
                                                 Map<Position, Piece> board) {
        Position source = positions.get(SOURCE);
        Position target = positions.get(TARGET);

        return move.stream()
                .anyMatch(moveUnit -> isMovablePositionByRecursion(source, target,
                        List.of(moveUnit.get(ROW), moveUnit.get(COLUMN)), board));
    }

    private static boolean isMovablePositionByRecursion(Position source, Position target, List<Integer> moveUnit,
                                                  Map<Position, Piece> board) {
        int row = moveUnit.get(ROW);
        int column = moveUnit.get(COLUMN);
        if (source.isOverRange()) {
            return false;
        }
        if (source.isSamePosition(target)) {
            return isBlankPosition(board.get(source));
        }
        return isMovablePositionByRecursion(source.findPossiblePosition(row, column), target, moveUnit, board);
    }

    private static boolean isBlankPosition(Piece piece) {
        if (piece == null) {
            return true;
        }
        return false;
        //return piece.isSameType(Type.BLANK);
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isColor(Piece piece) {
        return this.color == piece.color;
    }

    public String getSymbolByColor() {
        return type.getSymbol(color);
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
