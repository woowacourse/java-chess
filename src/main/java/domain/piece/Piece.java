package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Position;

public abstract class Piece {

    private final String name;
    private final boolean isBlack;
    private final Score score;

    protected Piece(String name, boolean color, Score score) {
        this.isBlack = color;
        this.name = name;
        this.score = score;
    }

    public Piece(String name) {
        this.isBlack = false;
        this.name = name;
        this.score = null;
    }

    public abstract boolean canMove(Board board, Position source, Position target);

    public boolean isSameColor(Piece piece) {
        return piece.isNotEmpty() && this.isBlack == piece.isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isNotEmpty() {
        return true;
    }

    public boolean isEmpty() {
        return false;
    }

    public String getName(){
        if(isBlack) {
            return name.toUpperCase();
        }
        return name;
    }

    public Score getScore(){
        return score;
    }

}
