package chess.controller.dto;

import chess.domain.player.Player;

public class PieceDto {

    private Player player;

    public PieceDto(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
