package chess.dao;

import chess.dao.dto.ChessGame;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class StaticMemoryChessDao implements ChessDao {
    private static final long AUTO_INCREMENT_BASE = 1;

    private final ConcurrentHashMap<Long, ChessGame> database = new ConcurrentHashMap<>();
    private long autoIncrement = AUTO_INCREMENT_BASE;

    @Override
    public long save(ChessGame entity) {
        long id = autoIncrement++;
        entity = new ChessGame(id, entity.getNextTurn(), entity.isRunning(), entity.getPieces());
        database.put(entity.getId(), entity);
        return id;
    }

    @Override
    public void update(ChessGame entity) {
        database.put(entity.getId(), entity);
    }

    @Override
    public void delete(long id) {
        database.remove(id);
    }

    @Override
    public Optional<ChessGame> findById(long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<ChessGame> findAllOnRunning() {
        return database.keySet().stream()
                .map(database::get)
                .collect(toList());
    }
}
