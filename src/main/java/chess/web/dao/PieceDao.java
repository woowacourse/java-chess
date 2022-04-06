package chess.web.dao;

import chess.web.dto.PieceDto;

public interface PieceDao {

    void save(PieceDto pieceDto);

    void deleteAll();

    void update(String position, PieceDto pieceDto);
}
