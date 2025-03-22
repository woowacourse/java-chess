package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;
import java.util.Optional;

public abstract class Piece {
    protected Position position;
    protected final Color color;

    protected Piece(Color color,Position position) {
        this.color = color;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAnotherTeam(Piece piece){
        return !this.color.equals(piece.color);
    }

    public boolean isSamePosition(Position position){
        return this.position.equals(position);
    }

    public abstract void moveTo(Position position, List<Piece> pieces);
    public abstract boolean canMove(Position position, List<Piece> pieces);
}
