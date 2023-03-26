package chess.dao;

import chess.dto.MoveDto;
import java.util.List;

public interface ChessDao {
    void saveHistory(MoveDto moveDto);

    void deleteAllHistory();

    List<MoveDto> selectAllHistory();
}
