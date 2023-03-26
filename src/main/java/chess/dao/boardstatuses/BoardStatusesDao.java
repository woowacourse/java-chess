package chess.dao.boardstatuses;

import chess.dto.ChessBoardStatus;
import java.util.List;
import java.util.Optional;

public interface BoardStatusesDao {

    List<Integer> findAvailableBoardIds();

    Optional<ChessBoardStatus> find(int boardId);

    void insertOrUpdate(int boardId, ChessBoardStatus status);

    void delete(int boardId);
    
}
