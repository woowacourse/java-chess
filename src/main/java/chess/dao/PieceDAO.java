package chess.dao;

import chess.dto.PieceDTO;

import java.sql.SQLException;

public interface PieceDAO {

    void addPiece(PieceDTO pieceDTO) throws SQLException;

    void updatePiece(PieceDTO pieceDTO) throws SQLException;
}
