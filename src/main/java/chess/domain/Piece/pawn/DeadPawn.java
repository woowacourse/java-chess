package chess.domain.Piece.pawn;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Dead;
import chess.domain.Piece.state.Initialized;
import chess.domain.Piece.team.Team;
import chess.domain.Position;

public class DeadPawn extends Dead {
    protected DeadPawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    protected boolean canMove(Position to, Initialized exPiece) {
        return false;
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        return null;
    }
}
