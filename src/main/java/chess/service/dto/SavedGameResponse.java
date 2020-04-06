package chess.service.dto;

import chess.entity.ChessGame;

import java.time.format.DateTimeFormatter;

public class SavedGameResponse {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long id;
    private String createdTime;

    public SavedGameResponse(ChessGame chessGame) {
        this.id = chessGame.getId();
        this.createdTime = chessGame.getCreatedTime().format(DATE_TIME_FORMATTER);
    }

    public Long getId() {
        return id;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
