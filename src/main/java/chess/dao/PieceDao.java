package chess.dao;

import chess.domain.ChessGame;
import chess.dto.PieceInfoDto;
import java.util.List;

public interface PieceDao {
    List<PieceInfoDto> findById(int gameId);

    void save(int gameId, ChessGame chessGame);

    void updateById(int gameId, List<PieceInfoDto> update);

    void deleteById(int gameId);

    void deleteAll();
}
