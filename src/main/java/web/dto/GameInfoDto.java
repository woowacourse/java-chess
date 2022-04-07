package web.dto;

import chess.domain.piece.Color;

public class GameInfoDto implements GameDto{

    private final String roomName;
    private final String turnColor;

    public GameInfoDto(String roomName) {
        this.roomName = roomName;
        this.turnColor = Color.WHITE.toString();
    }

    public GameInfoDto(String roomName, String turnColor) {
        this.roomName = roomName;
        this.turnColor = turnColor;
    }

    public GameInfoDto(String roomName, Color turnColor) {
        this.roomName = roomName;
        this.turnColor = turnColor.toString();
    }

    @Override
    public String getRoomName() {
        return roomName;
    }

    public String getTurnColor() {
        return turnColor;
    }
}
