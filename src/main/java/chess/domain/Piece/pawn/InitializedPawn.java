package chess.domain.Piece.pawn;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Initialized;
import chess.domain.Position;

public class InitializedPawn extends Initialized {
    InitializedPawn(Position position) {
        super(position);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        return null;
    }
}
