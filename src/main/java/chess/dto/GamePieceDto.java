package chess.dto;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

public class GamePieceDto {

    private String name;
    private PlayerColor playerColor;
    private Position position;

    public GamePieceDto(String name, PlayerColor playerColor, Position position) {
        this.name = name;
        this.playerColor = playerColor;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public Position getPosition() {
        return position;
    }
}
