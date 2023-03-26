package chess.repository;

import chess.dto.MoveDto;
import java.util.List;

public interface GameDao {
    void save(final MoveDto moveDto, final int roomId);

    List<MoveDto> findAllByRoomId(final int roomId);

    void deleteAll();
}
