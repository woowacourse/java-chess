package chess.dao;

import chess.dto.PieceDto;

import java.sql.SQLException;
import java.util.List;

public interface PieceDao {

    void addPiece(PieceDto pieceDto) throws SQLException;

    void updatePiece(PieceDto pieceDto) throws SQLException;

    List<PieceDto> findPiece() throws SQLException;
}
