package chess.persistence.dto;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;

import java.util.Map;

public class ChessGameStatusDto {
    private int gameId;
    private int roundNo;
    private String lastUser;
    private Map<Square, Piece> board;
    private double whiteScore;
    private double blackScore;
    private String winner;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public void setBoard(Map<Square, Piece> board) {
        this.board = board;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(double whiteScore) {
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(double blackScore) {
        this.blackScore = blackScore;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
