package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

public class Empty extends Piece {
    public Empty(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.EMPTY;
    }
    
    @Override
    public boolean isMovable(Piece destinationPiece) {
        return false;
    }
}
