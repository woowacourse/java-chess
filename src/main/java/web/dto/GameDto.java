package web.dto;

import chess.domain.piece.Color;

public class GameDto {

    private final String roomName;
    private final String turnColor;

    public GameDto(String roomName) {
        this.roomName = roomName;
        this.turnColor = Color.WHITE.toString();
    }

    public GameDto(String roomName, String turnColor) {
        this.roomName = roomName;
        this.turnColor = turnColor;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getTurnColor() {
        return turnColor;
    }
}
