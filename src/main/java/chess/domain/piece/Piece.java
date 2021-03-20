package chess.domain.piece;
import chess.domain.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Collections;
import java.util.List;

public abstract class Piece {
    protected final Owner owner;
    protected final List<Direction> directions;
    protected final Score score;

    public Piece(Owner owner, Score score, List<Direction> directions) {
        this.owner = owner;
        this.score = score;
        this.directions = directions;
    }

    public abstract boolean validateMove(Position source, Position target, Piece targetPiece);

    public final Score score(){
        return this.score;
    };

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

    public final boolean isEnemy(Piece other){
        return this.owner.isEnemy(other.owner);
    }

    public final boolean isSameTeam(Piece other){
        return this.owner.isSameTeam(other.owner);
    }

    public List<Direction> getDirections(){
        return Collections.unmodifiableList(directions);
    };

    public boolean isOwner(Owner owner) {
        return this.owner.isSameTeam(owner);
    }

    public abstract int getMaxDistance();

    public boolean isKing() {
        return this instanceof King;
    }
}
