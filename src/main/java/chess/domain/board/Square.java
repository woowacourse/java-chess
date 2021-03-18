package chess.domain.board;

import chess.domain.board.piece.Empty;
import chess.domain.board.piece.Piece;

public class Square {
    private final Position position;
    private Piece piece;

    public Square(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Square(Position position) {
        this(position, Empty.getInstance());
    }

    public void move(final Square target){
        if(piece instanceof Empty){
            throw new IllegalArgumentException();
        }

        piece.validateMove(this.position, target.position, isEnemy(target));
        target.piece = this.piece;

        this.piece = Empty.getInstance();
    }

    @Override
    public String toString() {
        return piece.makeSymbol();
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean hasEmpty(Square target) {
        return !(target.piece instanceof Empty);
    }

    public boolean isEnemy(Square target) {
        return this.piece.isEnemy(target.piece);
    }
}
