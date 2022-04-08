package chess.domain.dao;

import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PieceDaoImpl implements PieceDao {

    @Override
    public void save(Map<Position, Piece> board) {
        insertPiece(board);
    }

    private void insertPiece(Map<Position, Piece> board) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into piece (board_id, position, type, color) values (1, ?, ?, ?)";
        for (Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            executeInsertPiece(connection, sql, positionPieceEntry);
        }
    }

    private void executeInsertPiece(Connection connection, String sql,
                                    Entry<Position, Piece> positionPieceEntry) {
        Position position = positionPieceEntry.getKey();
        Piece piece = positionPieceEntry.getValue();
        try (final PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, position.stringName());
            statement.setString(2, piece.getSymbol());
            statement.setInt(3, piece.getColor().ordinal());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @Override
    public Map<Position, Piece> load() {

        final String sql = "select position, type, color from piece where board_id = 1";
        final Map<Position, Piece> pieces = new TreeMap<>();
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            executeLoad(pieces, statement);
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
        return pieces;
    }

    private void executeLoad(Map<Position, Piece> pieces, PreparedStatement statement) throws SQLException {
        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Position position = Position.from(resultSet.getString("position"));
            Type type = Type.from(resultSet.getString("type"));
            Piece piece = type.makePiece(Color.from(resultSet.getInt("color")));
            pieces.put(position, piece);
        }
    }

    @Override
    public boolean isExistsPieces() {
        final String sql = "select id from piece where board_id = 1";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @Override
    public void delete() {
        final String sql = "delete from piece where board_id = 1";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @Override
    public void updatePosition(String source, String target) {
        final String getSourcePieceSql = "select type, color from piece where position = ? and board_id = 1";
        final String updateSourceSql = "update piece set type = '.', color = 2 where position = ? and board_id = 1";
        final String updateTargetSql = "update piece set type = ?, color = ? where position = ? and board_id = 1";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement1 = connection.prepareStatement(getSourcePieceSql);
             final PreparedStatement statement2 = connection.prepareStatement(updateSourceSql);
             final PreparedStatement statement3 = connection.prepareStatement(updateTargetSql)) {

            statement1.setString(1, source);
            final ResultSet resultSet = statement1.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException();
            }
            final String type = resultSet.getString("type");
            final int color = resultSet.getInt("color");
            statement2.setString(1, source);
            statement2.executeUpdate();
            statement3.setString(1, type);
            statement3.setInt(2, color);
            statement3.setString(3, target);
            statement3.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }
}
