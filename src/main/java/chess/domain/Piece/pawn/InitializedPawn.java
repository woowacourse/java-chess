package chess.domain.Piece.pawn;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Initialized;
import chess.domain.Piece.team.Team;
import chess.domain.Position;

public class InitializedPawn extends Initialized {

    protected InitializedPawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        return null;
    }

    @Override
    protected boolean canMove(Position to, Initialized exPiece) {
        if (isNotSameTeam(exPiece)) {
            return false;
        }

        /*
        * 움직일 수 있다?
        * 1. 앞 2칸 전진(앞에 아무것도 없을 때)
        * 2. 앞 1칸 전진(앞에 아무것도 없을 때)
        * 3. 대각선 1칸(적이 대각선에 있을 때
        * */


        return false;
    }
}
