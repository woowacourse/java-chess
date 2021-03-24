package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Position;

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

    public abstract boolean canMove(Board board, Position source, Position target);

    public boolean isSameColor(Piece piece) {
        return piece.isNotEmpty() && this.isBlack == piece.isBlack;
    }

    public String getName() {
        if (isBlack) {
            return name.toUpperCase();
        }
        return name;
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

}
