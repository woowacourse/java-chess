package chess.repository;

import java.util.List;
import java.util.Optional;

import chess.domain.ChessGame;
import chess.domain.position.Position;

public interface GameRepository {
	void save(ChessGame game);

	Optional<ChessGame> findByName(String name);

	void updatePositionOfPiece(ChessGame game, Position from, Position to);

	void remove(String name);

	List<String> findAllNames();

	void updateStateOfGame(ChessGame game);
}
