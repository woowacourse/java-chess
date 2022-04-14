package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    protected static final String SOURCE = "source";
    protected static final String TARGET = "target";

    protected final Color color;
    private final double score;
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

    public boolean isMovableLine(Map<String, Position> positions, List<Direction> move, Map<Position, Piece> board) {
        return move.stream()
                .anyMatch(unit -> isMovableByRecursion(new HashMap<>(positions), unit, board));
    }

    private boolean isMovableByRecursion(Map<String, Position> positions, Direction unit, Map<Position, Piece> board) {
        Position source = positions.get(SOURCE).findPossiblePosition(unit.row(), unit.column());
        Position target = positions.get(TARGET);
        if (isImpossiblePosition(board, source, target)) {
            return false;
        }
        if (source.isSamePosition(target)) {
            return true;
        }
        positions.put(SOURCE, source);
        return isMovableByRecursion(positions, unit, board);
    }

    private boolean isImpossiblePosition(Map<Position, Piece> board, Position source, Position target) {
        if (source.isOverRange()) {
            return true;
        }
        if (!source.isSamePosition(target) && board.containsKey(source)) {
            return true;
        }
        return false;
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isColor(Piece piece) {
        return this.color == piece.color;
    }

    public String getColor() {
        return color.name().toLowerCase();
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
                "{" + "color=" + color.name().toLowerCase() + "}" ;
    }
}
