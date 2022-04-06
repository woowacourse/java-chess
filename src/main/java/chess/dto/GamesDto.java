package chess.dto;

import chess.domain.game.Game;
import java.util.ArrayList;
import java.util.List;

public class GamesDto {

	private final List<Game> games;

	private GamesDto(final List<Game> games) {
		this.games = new ArrayList<>(games);
	}

	public static GamesDto of(final List<Game> games) {
		return new GamesDto(games);
	}

	public List<Game> getGames() {
		return games;
	}
}
