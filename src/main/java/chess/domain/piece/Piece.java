package chess.domain.piece;

import chess.domain.board.Square;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Piece implements Movable {

    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public String getLetter() {
        return color.getApplyTypeName(type);
    }

    public abstract Set<Square> getAllCheatSheet(Square square);

    @Override
    public abstract Set<Square> getCheatSheet(Square square, Map<Square, Piece> board);

    protected Set<Square> findSquaresToRemove(Square s, int fileAddAmount, int rankAddAmount) {
        Set<Square> squaresToRemove = new HashSet<>();
        int file = 0;
        int rank = 0;
        for (int i = 0; i < 8; i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(s.addIfInBoundary(file, rank));
        }
        squaresToRemove.remove(s);
        return squaresToRemove;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public double getScore() {
        return type.getScore();
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }
}