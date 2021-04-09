package chess.repository.piece;

import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.List;

public interface PieceRepository {

    long insert(long roomId, Piece piece) throws SQLException;

    void update(Piece piece) throws SQLException;

    void deleteAll() throws SQLException;

    int count() throws SQLException;

    Piece findPieceById(long pieceId) throws SQLException;

    List<Piece> findPiecesByRoomId(long roomId) throws SQLException;

    void deletePieceById(long id) throws SQLException;
}
