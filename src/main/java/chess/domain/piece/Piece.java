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
    private final String piece;

    public Piece(double score, Color color) {
        this.score = score;
        this.color = color;
        this.piece = this.getClass().getSimpleName().toLowerCase();
    }

    public abstract boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board);

    public boolean isSameType(Class<? extends Piece> piece) {
        return this.getClass() == piece;
    }

    public boolean isMovableDot(List<Direction> coordinatesOfMovable, Position source, Position target) {
        return coordinatesOfMovable
                .stream()
                .anyMatch(coordinate -> isTargetPosition(source, coordinate, target));
    }

    private boolean isTargetPosition(Position source, Direction direction, Position target) {
        return source.findPossiblePosition(direction).isSamePosition(target);
    }

    public boolean isMovableLine(List<Position> positions, List<Direction> move,
                                                 Map<Position, Piece> board) {

        Position source = positions.get(SOURCE);
        Position target = positions.get(TARGET);

        return move.stream()
                .anyMatch(unit -> isMovableByRecursion(source.findPossiblePosition(unit.row(), unit.column()),
                        target, List.of(unit.row(), unit.column()), board));
    }

    private boolean isMovableByRecursion(Position source, Position target, List<Integer> unit,
                                                  Map<Position, Piece> board) {
        if (source.isOverRange()) {
            return false;
        }
        if (!source.isSamePosition(target) && board.containsKey(source)) {
            return false;
        }
        if (source.isSamePosition(target)) {
            return true;
        }
        return isMovableByRecursion(source.findPossiblePosition(unit.get(ROW), unit.get(COLUMN)), target, unit, board);
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

    public String getPiece() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String toString() {
        return "{" + "piece=" + piece + "}" +
                "{" + "color=" + color + "}" ;
    }
}
