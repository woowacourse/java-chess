package chess.dao.boardstatuses;

import chess.dto.ChessBoardStatus;
import java.util.List;
import java.util.Optional;

public interface BoardStatusesDao {

    List<Integer> findAllNotOverBoardIds();

    Optional<ChessBoardStatus> findByBoardId(int boardId);

    void insertOrUpdate(int boardId, ChessBoardStatus status);

    void delete(int boardId);

}
