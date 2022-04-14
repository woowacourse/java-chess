package chess.service.dto;

import java.util.ArrayList;
import java.util.List;

public class GamesDto {
    private final List<ChessGameDto> games;

    public GamesDto(List<ChessGameDto> games) {
        this.games = new ArrayList<>(games);
    }

    public List<ChessGameDto> getGames() {
        return games;
    }
}
