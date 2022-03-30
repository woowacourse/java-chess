package chess.repository;

import chess.domain.ChessGame;
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

    void deleteAll() {
        store.clear();
    }
}
