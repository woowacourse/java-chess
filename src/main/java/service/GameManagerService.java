package service;

import java.util.HashMap;
import java.util.Map;

import chess.GameManager;
import chess.board.Location;

public class GameManagerService {
	public Object move(GameManager gameManager, String now, String destination) {
		try {
			gameManager.movePiece(Location.of(now), Location.of(destination));
			return gameManager;
		} catch (IllegalArgumentException | NullPointerException e) {
			Map<String, String> message = new HashMap<>();
			message.put("error", e.getMessage());
			return message;
		}
	}

	public Object status(GameManager gameManager) {
		return gameManager.createStatistics();
	}

	public Object findWinner(GameManager gameManager) {
		Map<String, Object> model = new HashMap<>();
		try {
			model.put("winner", gameManager.findWinner());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.put("error", e.getMessage());
		}
		return model;
	}
}
