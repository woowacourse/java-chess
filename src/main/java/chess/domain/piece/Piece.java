package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {

    private final Position position;

    protected Piece(Position position) {
        this.position = position;
    }

    public static Piece of(Position position, PieceName pieceName) {
        if (pieceName == PieceName.KING) {
            return new King(position);
        }
        if (pieceName == PieceName.QUEEN) {
            return new Queen(position);
        }
        if (pieceName == PieceName.ROOK) {
            return new Rook(position);
        }
        if(pieceName == PieceName.BISHOP){
            return new Bishop(position);
        }
        if(pieceName == PieceName.KNIGHT){
            return new Knight(position);
        }
        if(pieceName == PieceName.PAWN){
            return new Pawn(position);
        }

        throw new RuntimeException();
    }
}
