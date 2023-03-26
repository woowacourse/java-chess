package chess.dao;

import chess.domain.piece.type.Piece;
import java.sql.Connection;
import java.util.List;

public interface PiecesDao {
    Connection getConnection();

    List<Piece> findAll();

    void insertAll(List<Piece> pieces);

    void delete();
}
