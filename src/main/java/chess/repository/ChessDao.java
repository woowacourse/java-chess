package chess.repository;

import chess.dto.MoveDto;
import java.util.List;

public interface ChessDao {
    void save(final MoveDto moveDto);

    List<MoveDto> findAll();

    void deleteAll();
}
