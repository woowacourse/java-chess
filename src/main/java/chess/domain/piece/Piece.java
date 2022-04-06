package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Name;
import java.util.List;

public abstract class Piece {
    private final Color color;
    private final Name name;
    private final double score;

    public Piece(Color color, String name, double score) {
        this.color = color;
        if (color == Color.BLACK) {
            name = name.toUpperCase();
        }
        this.name = new Name(name);
        this.score = score;
    }

    public static double computeScore(List<Piece> pieces) {
        return pieces.stream()
            .mapToDouble(piece -> piece.score)
            .sum();
    }

    public boolean isSameColor(Color other) {
        return color.isSameColor(other);
    }

    public abstract List<Position> getMovablePaths(Position source, ChessBoard board);

    public String getName() {
        return name.getName();
    }

    public String getColor() {
        return color.name();
    }

    public Color color() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Piece piece = (Piece)o;

        return getName() != null ? getName().equals(piece.getName()) : piece.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return name + "_" + color;
    }
}
