package chess.piece;

import chess.Color;
import chess.Position;
import java.util.List;

public abstract class Piece {
    protected Position position;
    protected final Color color;
    protected boolean isAlive;

    protected Piece(Color color,Position position) {
        this.color = color;
        this.position = position;
        isAlive = true;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAnotherTeam(Piece piece){
        return !isSameColor(piece.color);
    }

    public boolean isSameColor(Color color){
        return this.color.equals(color);
    }

    public boolean isSamePosition(Position position){
        return this.position.equals(position);
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void changePieceToDead(){
        isAlive = false;
    }

    public boolean isSamePieceType(PieceType pieceType){
        return getPieceType().equals(pieceType);
    }

    public Color getColor() {
        return color;
    }

    public abstract PieceType getPieceType();
    public abstract void moveTo(Position position, List<Piece> pieces);
    public abstract boolean canMove(Position position, List<Piece> pieces);
}
