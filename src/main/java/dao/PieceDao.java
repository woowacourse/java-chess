package dao;

import dto.PieceDto;
import dto.PositionDto;

import java.util.List;

public interface PieceDao {
    void save(List<PieceDto> pieceDtos);

    void save(PieceDto pieceDto);

    List<PieceDto> findAllPieces();

    void updatePiece(PositionDto beforePositionDto, PositionDto afterPositionDto, PieceDto pieceDto);

    void deleteByPosition(PositionDto positionDto);

    void deleteAllPieces();
}
