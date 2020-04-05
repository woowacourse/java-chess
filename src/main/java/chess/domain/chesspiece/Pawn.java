package chess.domain.chesspiece;

import chess.domain.game.Team;
import chess.domain.move.Position;

import static chess.domain.chesspiece.ChessPieceInfo.PAWN;
import static chess.domain.game.Team.BLACK;
import static chess.domain.game.Team.WHITE;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Team team) {
        super(PAWN, team);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void firstMoveComplete() {
        isFirstMove = false;
    }

    public void updateFirstMove(String team, Position position) {
        boolean isBlackTeam = BLACK.isSameTeamName(team);
        boolean isBlackPawnInitPosition = (position.getX() == 7);
        boolean blackPawnNotMoved = isBlackTeam && isBlackPawnInitPosition;
        boolean isWhiteTeam = WHITE.isSameTeamName(team);
        boolean isWhitePawnInitPosition = (position.getX() == 2);
        boolean whitePawnNotMoved = isWhiteTeam && isWhitePawnInitPosition;

        if (!(blackPawnNotMoved || whitePawnNotMoved)) {
            firstMoveComplete();
        }
    }
}
