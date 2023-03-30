package chess.dao;

import chess.service.dto.MoveDto;
import java.util.List;

public interface MoveDao {
    void save(final MoveDto moveDto);
    List<MoveDto> restart();
    void delete();
}
