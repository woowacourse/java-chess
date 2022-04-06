package chess.dao;

import chess.dto.PieceDto;

public interface PieceDao {

    void save(final int boardId, final PieceDto pieceDto);
}
