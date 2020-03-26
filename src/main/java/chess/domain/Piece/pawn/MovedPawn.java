package chess.domain.Piece.pawn;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Moved;
import chess.domain.Position;

public class MovedPawn extends Moved {
    MovedPawn(Position position) {
        super(position);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        return null;
    }
}
