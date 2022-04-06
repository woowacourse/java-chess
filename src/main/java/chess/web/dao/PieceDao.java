package chess.web.dao;

import chess.web.dto.PieceDto;
import java.util.List;

public interface PieceDao {

    void save(PieceDto pieceDto);

    void deleteAll();

    void update(PieceDto pieceDto);

    List<PieceDto> selectAll();
}
