package chess.repository;

import chess.manager.ChessManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryDao implements ChessRepository {
    private final Map<Long, ChessManager> memory = new HashMap<>();
    private final Object lock = new Object();
    private Long autoIncrement = 0L;

    @Override
    public Long save(ChessManager chessManager) {
        synchronized (lock) {
            autoIncrement++;
            memory.put(autoIncrement, chessManager);
            return autoIncrement;
        }
    }

    @Override
    public Optional<ChessManager> findById(Long id) {
        return Optional.ofNullable(memory.get(id));
    }
}
