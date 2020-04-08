package chess.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;

public class InMemoryChessGameDao implements ChessGameDao {
	private Map<Integer, ChessGame> memory;

	public InMemoryChessGameDao() {
		this.memory = new HashMap<>();
	}

	@Override
	public int create() {
		int id = memory.size() + 1;
		memory.put(id, new ChessGame(new Ready()));
		return id;
	}

	@Override
	public List<Integer> findAll() {
		return new ArrayList<>(memory.keySet());
	}

	@Override
	public Optional<ChessGame> findById(int id) {
		return Optional.ofNullable(memory.get(id));
	}

	@Override
	public void updateById(int id, ChessGame chessGame) {
		memory.replace(id, chessGame);
	}

	@Override
	public void deleteById(int id) {
		memory.remove(id);
	}
}
