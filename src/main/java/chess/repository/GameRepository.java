package chess.repository;

import java.util.List;
import java.util.Optional;

import chess.domain.ChessGame;
import chess.domain.command.Command;

public interface GameRepository {
	void save(ChessGame game);

	Optional<ChessGame> findByName(String name);

	void updateGame(ChessGame game, Command command);

	void remove(String name);

	List<String> findAllNames();
}
