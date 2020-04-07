package chess.dto;

import chess.domain.piece.Team;

import java.util.Map;

public class ChessDto {
    private Map<String, String> board;
    private double blackScore;
    private double whiteScore;
    private Team team;

    public Map<String, String> getBoard() {
        return board;
    }

    public void setBoard(Map<String, String> board) {
        this.board = board;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(double blackScore) {
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(double whiteScore) {
        this.whiteScore = whiteScore;
    }
}
