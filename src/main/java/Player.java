import chess.board.Location;
import chess.team.Team;

public class Player {
    private final ChessSet chessSet;
    private final Team team;

    public Player(ChessSet chessSet, Team team) {
        this.chessSet = chessSet;
        this.team = team;
    }

    public boolean isNotSameTeam(Team team) {
        return this.team.equals(team);
    }

    public void deletePieceIfExistIn(Location destination) {
        chessSet.remove(destination);
    }

    public boolean hasNotKing() {
        return chessSet.hasNotKing();
    }
}


