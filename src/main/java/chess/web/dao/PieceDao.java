package chess.web.dao;

import chess.web.dto.PieceDto;

public interface PieceDao {

    void save(PieceDto pieceDto);

    void deleteAll();
}
