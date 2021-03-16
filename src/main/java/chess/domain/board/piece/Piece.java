package chess.domain.board.piece;

import chess.domain.board.Position;

public abstract class Piece {

    private Owner owner;

    public Piece(Owner owner) {
        this.owner = owner;
    }

    public abstract boolean isValidMove(Position source, Position target);

    public abstract Score score();
}
