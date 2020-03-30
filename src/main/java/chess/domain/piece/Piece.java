package chess.domain.piece;

import chess.domain.square.Square;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {
    protected final Color color;
    private final String name;
    private final double score;

    public Piece(Color color, String name, double score) {
        Objects.requireNonNull(color, "color은 필수입니다");
        Objects.requireNonNull(name, "name은 필수입니다");

        this.color = color;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Set<Square> calculateScope(Square square);

    public abstract Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board);

    public Set<Square> findSquaresToRemove(Square s, int fileAddAmount, int rankAddAmount) {
        Set<Square> squaresToRemove = new HashSet<>();
        int file = 0;
        int rank = 0;
        for (int i = 0; i < 8; i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(s.movedSquareInBoundary(file, rank));
        }
        squaresToRemove.remove(s);
        return squaresToRemove;
    }

    public void validateNotNull(Square square, Map<Square, Piece> board) {
        if (Objects.isNull(square) || Objects.isNull(board)) {
            throw new IllegalArgumentException("null이면 안됩니다");
        }
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public double getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }
}