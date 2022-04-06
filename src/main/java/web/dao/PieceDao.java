package web.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PieceDao {

    List<Piece> load() throws SQLException;

    String findTurn() throws SQLException;

    Map<Position, Piece> findPieces() throws SQLException;

    void savePiece(final String position, final Piece piece) throws SQLException;

    void deletePiece(final String position) throws SQLException;

    void updateTurn(Color color) throws SQLException;

    void removeAll() throws SQLException;

    void initTurn() throws SQLException;
}
