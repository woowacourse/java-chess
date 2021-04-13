package chess.controller.web.dto.game;

import chess.domain.game.Game;

public class GameRequestDto {

    private final String whiteUsername;
    private final String blackUsername;
    private final String roomName;

    public GameRequestDto(final String whiteUsername, final String blackUsername, final String roomName) {
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.roomName = roomName;
    }

    public Game toGame() {
        return Game.of(this.roomName, this.whiteUsername, this.blackUsername);
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public String getRoomName() {
        return roomName;
    }
}
