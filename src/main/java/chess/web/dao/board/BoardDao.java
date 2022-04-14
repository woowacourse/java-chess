package chess.web.dao.board;

import java.util.Map;

public interface BoardDao {
    void save(int roomId, Map<String, String> board);

    Map<String, String> findAll();

    void updateBoard(int roomId, Map<String, String> board);

    void remove(int roomId);
}
