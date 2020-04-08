package chess.dao;

import chess.entity.ChessGame;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryChessDAO implements ChessDAO {
    private final Map<Long, ChessGame> memory = new Hashtable<>();
    private Long autoIncrement = 0L;

    @Override
    public ChessGame save(ChessGame entity) {
        autoIncrement++;
        entity = new ChessGame(autoIncrement, LocalDateTime.now(ZoneId.of("Asia/Seoul")), entity);
        memory.put(autoIncrement, entity);
        return entity;
    }

    @Override
    public Optional<ChessGame> findById(Long id) {
        return Optional.ofNullable(memory.get(id));
    }

    @Override
    public void update(ChessGame entity) {
        memory.replace(entity.getId(), entity);
    }

    @Override
    public List<ChessGame> findAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public List<ChessGame> findAllByActive() {
        return memory.values().stream()
                .filter(ChessGame::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        memory.clear();
    }
}
