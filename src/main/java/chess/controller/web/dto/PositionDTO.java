package chess.controller.web.dto;

import chess.domain.location.Position;

public class PositionDTO {
    private String key;

    public PositionDTO(Position position) {
        this.key = position.toKey();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
