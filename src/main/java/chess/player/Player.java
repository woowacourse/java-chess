package chess.player;

import chess.game.ChessSet;
import chess.location.Location;
import chess.score.Score;
import chess.team.Team;

public class Player {
    private final ChessSet chessSet;
    private final Team team;

    public Player(ChessSet chessSet, Team team) {
        this.chessSet = chessSet;
        this.team = team;
    }

    public boolean is(Team team) {
        return this.team.equals(team);
    }

    public boolean isNotSame(Team team) {
        return !is(team);
    }

    public void deletePieceIfExistIn(Location location) {
        chessSet.remove(location);
    }

    public boolean hasNotKing() {
        return chessSet.hasNotKing();
    }

    public Score calculateScoreExceptPawnReduce() {
        return chessSet.calculateScoreExceptPawnReduce();
    }

    public void movePiece(Location now, Location after) {
        chessSet.movePiece(now, after);
    }

    public Team getTeam() {
        return team;
    }

    public String getTeamName() {
        return team.name();
    }
}


