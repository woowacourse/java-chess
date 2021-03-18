package chess.domain.board.piece;
import chess.domain.board.Position;
import chess.domain.board.Square;

public abstract class Piece {
    protected Owner owner;

    public Piece(Owner owner) {
        this.owner = owner;
    }

    public abstract void validateMove(Position source, Position target, boolean hasEnemy);

    public abstract Score score();

    public final String makeSymbol(){
        return decideUpperOrLower(getSymbol());
    }

    private final String decideUpperOrLower(String symbol){
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
}
