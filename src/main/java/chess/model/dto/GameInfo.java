package chess.model.dto;

import java.util.Map;

public class GameInfo {
    private String turn;
    private boolean isFinished;
    private double blackScore;
    private double whiteScore;
    private boolean canMove;
    private Map<String, String> map;

    public String getTurn() {
        return turn;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setTurn(String turn) {
        this.turn = turn;
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

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
