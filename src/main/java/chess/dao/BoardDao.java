package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMapper;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public Map<Position, Piece> loadAllPieces() {
        final String sql = "select position, name from board";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();){
            Map<Position, Piece> pieceByPosition = new HashMap<>();
            placePieceByPosition(resultSet, pieceByPosition);
            return pieceByPosition;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void placePieceByPosition(ResultSet resultSet, Map<Position, Piece> pieceByPosition) throws SQLException {
        while (resultSet.next()) {
            String position = resultSet.getString(1);
            String rawPiece = resultSet.getString(2);

            pieceByPosition.put(Position.of(position),
                    PieceMapper.convert(rawPiece));
        }
    }

    public void saveAllPieces(Map<Position, Piece> pieces) {
        for (Map.Entry<Position, Piece> piece : pieces.entrySet()) {
            insertPiece(piece.getKey(), piece.getValue());
        }
    }

    public void removeAllPieces() {
        final String deleteSql = "delete from board";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);){
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void insertPiece(Position position, Piece piece) {
        final String insertSql = "insert into board(position, name) values (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql);) {
            preparedStatement.setString(1, position.toString());
            preparedStatement.setString(2, piece.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public boolean isExist() {
        final String sql = "select * from board";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void deleteByPosition(Position position) {
        final String sql = "delete from board where position=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, position.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        final String password = "root";
        final String userName = "root";
        final String database = "chess";
        final String option = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        return DriverManager.getConnection("jdbc:mysql://localhost:3308/" + database + option,
                userName, password);
    }
}
