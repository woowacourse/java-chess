package chess.dao.boardstatuses;

import chess.dto.ChessBoardStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryBoardStatusesDao implements BoardStatusesDao {

    private final Map<Integer, ChessBoardStatus> repository = new HashMap<>();

    @Override
    public List<Integer> findAllNotOverBoardIds() {
        return repository.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isOver())
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChessBoardStatus> findByBoardId(final int boardId) {
        return Optional.ofNullable(repository.get(boardId));
    }

    @Override
    public void insertOrUpdate(final int boardId, final ChessBoardStatus status) {
        repository.put(boardId, status);
    }

    @Override
    public void delete(final int boardId) {
        repository.remove(boardId);
    }

}
