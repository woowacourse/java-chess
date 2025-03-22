package chess.domain.game;

import chess.domain.Position;
import chess.domain.TeamColor;

public class Finished implements Turn{
    public Finished(TeamColor winnerColor) {
        this.winnerColor = winnerColor;
    }

    private final TeamColor winnerColor;


    @Override
    public Turn movePiece(Position start, Position target) {
        return null;
    }

    @Override
    public TeamColor getTeamColor() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
