package chess.player;

import chess.game.ChessSet;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;

public class Player {
    private final ChessSet chessSet;
    private final Team team;

    public Player(ChessSet chessSet, Team team) {
        this.chessSet = chessSet;
        this.team = team;
    }

    public boolean isSame(Team team) {
        return this.team.equals(team);
    }

    public boolean isNotSame(Team team) {
        return !isSame(team);
    }

    public void deletePieceIfExistIn(Piece piece) {
        chessSet.remove(piece);
    }

    public boolean hasNotKing() {
        return chessSet.hasNotKing();
    }

    public Score calculateScoreExceptPawnReduce() {
        return chessSet.calculateScoreExceptPawnReduce();
    }

    public Team getTeam() {
        return team;
    }

    public String getTeamName() {
        return team.name();
    }
}


