package chess.repository;

import chess.domain.ChessGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryGameRepository implements GameRepository {

    private final static Map<Long, ChessGame> store = new ConcurrentHashMap<>();
    private static int nextId = 1;


    @Override
    public void save(ChessGame game) {
        game = new ChessGame((long) nextId++, game.getState());
        store.put(game.getId(), game);
    }

    @Override
    public Optional<ChessGame> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public List<ChessGame> findAll() {
        return new ArrayList<>(store.values());
    }

    void deleteAll() {
        store.clear();
    }
}
