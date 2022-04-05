package chess.dao;

import chess.web.dto.PieceDto;
import java.util.List;
import java.util.Optional;

public interface PieceDao {

    void save(PieceDto pieceDto);

    Optional<PieceDto> findById(String id);

    void remove(String id);

    List<PieceDto> findAll();

    void removeAll();
}
