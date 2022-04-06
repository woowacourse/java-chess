package chess.dao;

import chess.domain.GameState;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeGameDao implements GameDao {

    private final Map<Long, GameState> games;

    public FakeGameDao() {
        this.games = new HashMap<>();
    }

    @Override
    public void save(long id) {
        games.put(id, GameState.READY);
    }

    @Override
    public Optional<GameState> load(long id) {
        if (games.containsKey(id)) {
            return Optional.of(games.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void updateState(long id, GameState gameState) {
        games.put(id, gameState);
    }

    @Override
    public void delete(long id) {
        games.remove(id);
    }

    @Override
    public void deleteAll() {
        games.clear();
    }
}
