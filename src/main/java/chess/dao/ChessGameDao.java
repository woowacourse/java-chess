package chess.dao;

import chess.dto.MoveHistory;
import java.util.List;

public interface ChessGameDao {

    void save(MoveHistory moveHistory);

    List<MoveHistory> findAll();

    void deleteAll();
}
