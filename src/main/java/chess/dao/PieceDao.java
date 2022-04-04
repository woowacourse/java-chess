package chess.dao;

import chess.web.dto.PieceDto;
import java.util.List;

public interface PieceDao {

    void save(PieceDto pieceDto);

    List<PieceDto> findAll();
}
