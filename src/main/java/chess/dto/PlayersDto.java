package chess.dto;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Color;
import chess.domain.player.Players;

public class PlayersDto {

    private final Map<String, PlayerDto> playerDtos;

    private PlayersDto(final Map<String, PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }

    public static PlayersDto toDto(final Players players) {
        return new PlayersDto(Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Color::getName,
                        color -> PlayerDto.toDto(players.getPiecesByPlayer(color)))
                ));
    }

    public Map<String, PlayerDto> getPlayerDtos() {
        return playerDtos;
    }
}
