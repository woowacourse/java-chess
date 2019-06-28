package chess.domain.board;

public class ChessRound {
    double whiteTeamScore;
    double blackTeamScore;

    public ChessRound(double whiteTeamScore, double blackTeamScore) {
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }
}
