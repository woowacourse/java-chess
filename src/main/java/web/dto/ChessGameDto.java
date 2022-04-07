package web.dto;

import chess.Score;
import chess.piece.Color;

public class ChessGameDto {

    private final int id;
    private final String name;
    private final GameStatus status;
    private final Score blackScore;
    private final Score whiteScore;
    private final Color currentColor;
    private final String winner;

    public ChessGameDto(int id, String name, GameStatus status, Score blackScore, Score whiteScore,
                        Color currentColor) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.currentColor = currentColor;
        this.winner = "";
    }

    public ChessGameDto(int id, String name, GameStatus status, Score blackScore, Score whiteScore, Color currentColor,
                        String winner) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.currentColor = currentColor;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Score getBlackScore() {
        return blackScore;
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public String getWinner() {
        return winner;
    }
}
