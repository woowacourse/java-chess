package chess.dao.chess;

import java.util.Map;

public interface BoardDao {

    void save(final int boardId, final Map<String, String> board);

    Map<String, String> findById(final int boardId);

    void remove(int chess);
}
