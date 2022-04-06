package chess.dao;

import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.util.List;

public interface PieceDao {

    void remove(Position position);

    void removeAll();

    void saveAll(List<PieceDto> pieceDtos);

    void save(PieceDto pieceDto);

    List<PieceDto> findAll();

    void update(Position source, Position target);
}
