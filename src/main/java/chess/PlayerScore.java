package chess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import chess.exception.NotFoundPlayerException;

public class PlayerScore {
	private static final int INITIAL_SCORE = 0;

	private final Map<Player, Score> playerScores;

	public PlayerScore() {
		this.playerScores = new HashMap<>();
		Arrays.stream(Player.values())
				.forEach(player -> playerScores.put(player, new Score(INITIAL_SCORE)));
	}

	public Score addScore(Player player, Score score) {
		Score playerScore = getScore(player);
		Score addedScore = playerScore.add(score);
		playerScores.put(player, addedScore);
		return addedScore;
	}

	public Score getScore(Player player) {
		Score playerScore = playerScores.get(player);
		if (playerScore == null) {
			throw new NotFoundPlayerException();
		}
		return playerScore;
	}
}
