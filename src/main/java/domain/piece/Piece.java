package domain.piece;

import domain.board.Score;
import domain.position.Position;
import java.util.Map;

public abstract class Piece {

    private final String name;
    private final boolean isBlack;
    private final Score score;

    protected Piece(String name, boolean isBlack, Score score) {
        this.name = name;
        this.isBlack = isBlack;
        this.score = score;
    }

    public Piece(String name) {
        this.name = name;
        this.isBlack = false;
        this.score = null;
    }

    public boolean isSameColor(Piece piece) {
        return piece.isNotEmpty() && this.isBlack == piece.isBlack;
    }

    public String getName() {
        if (isBlack) {
            return name.toUpperCase();
        }
        return name;
    }

    public void validateMove(Map<Position, Piece> board, Position source, Position target) {
        if (!canMove(board, source, target)) {
            throw new IllegalArgumentException("[Error] 해당 기물은 target 위치로 이동할 수 없습니다.");
        }
    }

    public Score getScore() {
        return score;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean isNotEmpty() {
        return true;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    protected abstract boolean canMove(Map<Position, Piece> board, Position source,
        Position target);

}
