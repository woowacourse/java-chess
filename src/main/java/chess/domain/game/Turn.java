package chess.domain.game;

import chess.domain.pieces.Piece;
import chess.domain.pieces.Team;

public enum Turn {
    WHITE_TEAM_TURN(Team.WHITE),
    BLACK_TEAM_TURN(Team.BLACK);

    final Team team;

    Turn (Team team) {
        this.team = team;
    }

    public boolean isCorrectTurn(Piece piece) {
        return this.team == piece.getTeam();
    }

    public boolean isWhiteTeamTurn() {
        return this == WHITE_TEAM_TURN;
    }

    public boolean isBlackTeamTurn() {
        return this == BLACK_TEAM_TURN;
    }
}
