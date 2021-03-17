package chess.domain.board.piece;

import chess.domain.board.Position;

import java.util.Locale;

public abstract class Piece {
    private Owner owner;

    public Piece(Owner owner) {
        this.owner = owner;
    }

    public abstract boolean isValidMove(Position source, Position target);

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
}
