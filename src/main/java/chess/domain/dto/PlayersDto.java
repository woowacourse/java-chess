package chess.domain.dto;

import chess.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {

    private final List<PlayerDto> players;

    public PlayersDto(List<Player> players) {
        this.players = List.copyOf(players).stream()
                .map(player -> new PlayerDto(player.getOriginPieces(), player.getColor()))
                .collect(Collectors.toList());
    }

}
