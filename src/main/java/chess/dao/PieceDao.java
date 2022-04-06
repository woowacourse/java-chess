package chess.dao;

import chess.dto.PieceDto;

import java.util.List;

public interface PieceDao {

    void removeAll();

    void saveAll(List<PieceDto> pieceDtos);

    void save(PieceDto pieceDto);

    List<PieceDto> findAll();
}
