package chess.domain.Piece.pawn;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Dead;
import chess.domain.Position;

public class DeadPawn extends Dead {
    protected DeadPawn(Position position) {
        super(position);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        return null;
    }
}
