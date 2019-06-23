package chess.domain.board;

import chess.domain.piece.core.Team;

public class ChessRound {
    double whiteTeamScore;
    double blackTeamScore;

    public ChessRound(double whiteTeamScore, double blackTeamScore) {
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
    }

    public Team findWinner() {
        return whiteTeamScore > blackTeamScore ? Team.WHITE : Team.BLACK;
    }
}
