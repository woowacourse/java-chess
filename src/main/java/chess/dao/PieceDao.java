package chess.dao;

import java.sql.SQLException;
import java.util.List;

public interface PieceDao {

    void addPiece(PieceEntity pieceEntity) throws SQLException;

    void updatePiece(PieceEntity pieceEntity) throws SQLException;

    List<PieceEntity> findPiece() throws SQLException;
}
