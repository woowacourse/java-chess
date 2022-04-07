package chess.dao;

import chess.dto.response.Turn;
import chess.piece.Color;

public interface BoardDao {
    Long save(Color color);

    Turn findById(Long boardId);

    void update(Long boardId, Color color);
}
