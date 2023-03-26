package chess.dao;

import chess.dto.MoveDto;
import java.util.List;

public interface ChessGameDao {

    void saveMove(MoveDto moveDto);

    List<MoveDto> findAll();

    void deleteAll();
}
