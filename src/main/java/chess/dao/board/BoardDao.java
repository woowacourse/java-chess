package chess.dao.board;

import java.util.Map;

public interface BoardDao {

    void save(final int boardId, final Map<String, String> board, final boolean isLowerTeamTurn);

    Map<String, String> findById(final int boardId);

    boolean isLowerTeamTurnByBoardId(final int boardId);

    void remove(int boardId);
}
