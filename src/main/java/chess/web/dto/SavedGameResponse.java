package chess.web.dto;

import chess.entity.ChessEntity;

import java.time.format.DateTimeFormatter;

public class SavedGameResponse {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long id;
    private String createdTime;

    public SavedGameResponse(ChessEntity chessEntity) {
        this.id = chessEntity.getId();
        this.createdTime = chessEntity.getCreatedTime().format(DATE_TIME_FORMATTER);
    }

    public Long getId() {
        return id;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
