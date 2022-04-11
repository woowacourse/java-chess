package web.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.Map;

public interface PieceDao {

    Map<Position, Piece> findPieces(Long gameId) throws SQLException;

    void savePiece(String position, Piece piece, Long gameId) throws SQLException;

    void deletePiece(String position, Long id) throws SQLException;
}
