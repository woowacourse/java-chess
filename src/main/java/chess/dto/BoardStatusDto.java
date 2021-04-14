package chess.dto;

import chess.domain.board.BoardStatus;

public class BoardStatusDto {
    private static final String SCORE = "";

    private String lastTurn;
    private String blackScore;
    private String whiteScore;

    public BoardStatusDto() {
    }

    public BoardStatusDto(BoardStatus boardStatus) {
        this.lastTurn = boardStatus.getLastTurn().name();
        this.blackScore = boardStatus.getBlackScore().getScore() + SCORE;
        this.whiteScore = boardStatus.getWhiteScore().getScore() + SCORE;
    }

    public String getLastTurn() {
        return lastTurn;
    }

    public void setLastTurn(String lastTurn) {
        this.lastTurn = lastTurn;
    }

    public String getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(String blackScore) {
        this.blackScore = blackScore;
    }

    public String getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(String whiteScore) {
        this.whiteScore = whiteScore;
    }
}
