package chess.repository;

import java.util.List;
import java.util.Optional;

import chess.domain.ChessGame;

public interface GameRepository {
	void save(ChessGame game);

	Optional<ChessGame> findByName(String name);

	void update(ChessGame chessGame);

	void remove(String name);

	List<String> findAllNames();
}
