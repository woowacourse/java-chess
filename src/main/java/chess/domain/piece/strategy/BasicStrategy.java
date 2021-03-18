package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class BasicStrategy implements MoveStrategy{

    @Override
    public boolean isMovable(Piece piece, Board board) {
        return false;
    }

/*    public double makeSlope(Position source, Position target){
        source.calculateSlopeWith(target);
    }

    public boolean isDiagonal(){
        Direction.diagonalDirection().contains()
    }*/
}
