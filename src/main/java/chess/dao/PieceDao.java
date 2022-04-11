package chess.dao;

import chess.dto.PieceDto;
import java.util.List;

public interface PieceDao {
    void create(List<PieceDto> pieces, int boardId);

    void updatePosition(String from, String to);

    List<PieceDto> findByBoardId(int id);

    void delete(String position);
}
