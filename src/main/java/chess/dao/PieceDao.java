package chess.dao;

import chess.dto.PieceInfoDto;
import java.util.List;

public interface PieceDao {
    List<PieceInfoDto> findById(int gameId);

    void save(int gameId, List<PieceInfoDto> save);

    void updateById(int gameId, List<PieceInfoDto> update);

    void deleteById(int gameId);

    void deleteAll();
}
