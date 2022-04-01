package chess.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.domain.ChessGame;

public class MapGameRepository implements GameRepository {

	private final Map<String, ChessGame> repository;

	public MapGameRepository() {
		repository = new HashMap<>();
	}

	@Override
	public void save(ChessGame game) {
		validateDuplicateName(game);
		repository.put(game.getId(), game);
	}

	private void validateDuplicateName(ChessGame game) {
		if (findByName(game.getName()).isPresent()) {
			throw new IllegalStateException("해당 이름의 게임이 이미 존재합니다.");
		}
	}

	@Override
	public Optional<ChessGame> findByName(String name) {
		return repository.entrySet().stream()
			.filter(entry -> entry.getValue().getName().equals(name))
			.findAny()
			.map(Map.Entry::getValue);
	}
}
