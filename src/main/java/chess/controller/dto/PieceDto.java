package chess.controller.dto;

import chess.domain.game.Player;

public class PieceDto {
    private String name;
    private Player player;

    public PieceDto(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }
}
