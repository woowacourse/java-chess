package chess.repository;

import chess.domain.ChessGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemoryGameRepository implements GameRepository {

    private final static Map<Long, ChessGame> store = new ConcurrentHashMap<>();
    private static int nextId = 1;


    @Override
    public void save(ChessGame game) {
        game = new ChessGame((long) nextId++, game.getState(), game.getParticipant());
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

    @Override
    public List<ChessGame> findHistorysByMemberId(Long memberId) {
        return store.values()
                .stream()
                .filter(ChessGame::isEnd)
                .filter(game -> Objects.equals(game.getParticipant().getBlackId(), memberId)
                        || Objects.equals(game.getParticipant().getWhiteId(), memberId))
                .collect(Collectors.toList());
    }

    void deleteAll() {
        store.clear();
    }
}
