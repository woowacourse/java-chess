package chess.domain.piece;

import chess.domain.Team;
import chess.domain.square.Rank;

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
    public boolean isValidPawnMove(final Rank rank, final int fileInterval, final int rankInterval) {
        return false;
    }
}
