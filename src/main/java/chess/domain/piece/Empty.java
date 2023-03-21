package chess.domain.piece;

import chess.domain.Team;

public class Empty extends Piece{

    public Empty() {
        super(Team.EMPTY, PieceType.EMPTY);
    }

    @Override
    public boolean isValidMove(int fileInterval, int rankInterval, Piece target) {
        return false;
    }

    @Override
    public boolean isValidTeam(Piece target) {
        return false;
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
