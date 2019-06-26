package dao;

import dto.NavigatorDto;
import dto.PieceDto;

import java.util.List;

public interface PieceDao {
    List<PieceDto> findByGameId(int gameId);
    int addPiece(PieceDto pieceDto);
    int updatePiece(NavigatorDto navigatorDto);
    int deletePiece(PieceDto pieceDto);
}
