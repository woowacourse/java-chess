package chess.dao;

import chess.domain.ChessBoard;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryChessBoardDao implements ChessBoardDao {

    private final Map<Integer, ChessBoard> repository = new HashMap<>();

    @Override
    public Optional<ChessBoard> find() {
        if (repository.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(repository.get(1));
    }

    @Override
    public void insert(final ChessBoard chessBoard) {
        repository.put(1, chessBoard);
    }

    @Override
    public void delete() {
        repository.clear();
    }

}
