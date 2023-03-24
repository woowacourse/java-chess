package domain.dao;

import domain.Location;
import domain.piece.Piece;
import domain.type.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface ChessInformationDao {

    Map<Location, Piece> find(final String id, final Connection connection) throws SQLException;

    Color findColor(final String id, final Connection connection) throws SQLException;

    Integer count(final String id, final Connection connection) throws SQLException;

    Void insert(final Map<Location, Piece> board, final String boardId, final Color color, final Connection connection)
        throws SQLException;

    Void update(final Map<Location, Piece> board, final String boardId, final Color color, final Connection connection)
        throws SQLException;
}
