package chess.web.dto;

import chess.domain.game.state.Player;

public class RoomDto {
    private int number;
    private final String name;
    private final Player player;

    public RoomDto(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    private RoomDto(int number, String name, Player player) {
        this.number = number;
        this.name = name;
        this.player = player;
    }

    public static RoomDto of(int number, String name, String player) {
        return new RoomDto(number, name, Player.valueOf(player));
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getPlayer() {
        return player.name();
    }
}
