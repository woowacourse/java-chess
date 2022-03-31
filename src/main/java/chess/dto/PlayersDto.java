package chess.dto;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Color;
import chess.domain.player.Players;

public class PlayersDto {

    private final Map<ColorDto, PlayerDto> playerDtos;

    private PlayersDto(final Map<ColorDto, PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }

    public static PlayersDto toDto(final Players players) {
        return new PlayersDto(Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        ColorDto::toDto,
                        color -> PlayerDto.toDto(players.getPiecesByPlayer(color)))
                ));
    }

    public Map<ColorDto, PlayerDto> getPlayerDtos() {
        return playerDtos;
    }
}
