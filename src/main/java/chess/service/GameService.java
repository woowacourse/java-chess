package chess.service;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.repository.GameRepository;
import chess.repository.MemoryGameRepository;

public class GameService {

	private static final String NOT_EXIST_GAME = "해당하는 이름의 게임이 존재하지 않습니다.";

	private final GameRepository gameRepository = new MemoryGameRepository();

	public void saveGame(ChessGame game) {
		gameRepository.save(game);
	}

	public ChessGame findGame(String name) {
		return gameRepository.findByName(name)
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_GAME));
	}

	public ChessGame updateGame(Command command, String name) {
		ChessGame updatedGame = findGame(name).execute(command);
		gameRepository.update(updatedGame.getId(), updatedGame.getState());
		return updatedGame;
	}
}
