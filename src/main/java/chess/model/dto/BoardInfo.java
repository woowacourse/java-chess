package chess.model.dto;

import chess.utils.JsonUtils;

import java.util.Map;

public class BoardInfo {
    private String turn;
    private boolean isFinished;
    private double whiteScore;
    private double blackScore;
    private Map<String, String> pieceMap;

    public void setPieceMap(Map<String, String> pieceMap) {
        this.pieceMap = pieceMap;
    }

    public Map<String, String> getPieceMap() {
        return pieceMap;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setWhiteScore(double whiteScore) {
        this.whiteScore = whiteScore;
    }

    public void setBlackScore(double blackScore) {
        this.blackScore = blackScore;
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }
}