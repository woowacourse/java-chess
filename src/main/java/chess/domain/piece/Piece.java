package chess.domain.piece;
import chess.controller.direction.Direction;
import chess.domain.board.position.Position;

import java.util.List;

public abstract class Piece {
    protected final Owner owner;

    public Piece(Owner owner) {
        this.owner = owner;
    }

    public abstract boolean validateMove(Position source, Position target, Piece targetPiece);

    public abstract Score score();

    public final String decideUpperOrLower(String symbol){
        if(owner.equals(Owner.BLACK)){
            return symbol.toUpperCase();
        }

        if(owner.equals(Owner.WHITE)){
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public abstract String getSymbol();

    public boolean isEnemy(Piece other){
        if(owner.equals(Owner.BLACK)){
            return other.owner == Owner.WHITE;
        }

        if(owner.equals(Owner.WHITE)){
            return other.owner == Owner.BLACK;
        }

        throw new IllegalArgumentException();
    }

    public boolean isSameTeam(Piece other){
        return owner.equals(other.owner);
    }

    public abstract List<Direction> getDirections();

    public abstract int getMaxDistance();

    public boolean isEmpty(){
        return this instanceof Empty;
    }
}
