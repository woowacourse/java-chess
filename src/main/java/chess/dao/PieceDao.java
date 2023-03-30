package chess.dao;

import chess.dto.PieceInfoDto;
import java.util.List;

public interface PieceDao {
    List<PieceInfoDto> findAllById(int gameId);
    void create(int gameId, PieceInfoDto pieceInfoDto);
    void updateById(int gameId, PieceInfoDto pieceInfoDto);
    void deleteById(int gameId);
    void deleteAll();
}
