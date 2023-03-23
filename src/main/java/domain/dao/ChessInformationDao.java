package domain.dao;

import domain.Location;
import domain.piece.Piece;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface ChessInformationDao {

    Map<Location, Piece> find(final String id, final Connection connection) throws SQLException;

    Integer count(final String id, final Connection connection) throws SQLException;

    Void insert(final Map<Location, Piece> board, final String boardId, final Connection connection)
        throws SQLException;

    Integer update(final Map<Location, Piece> board, final String boardId, final Connection connection) throws SQLException;
}
