package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceConverter;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {

    public Map<Position, Piece> load() {
        final String sql = "select position, name from piece";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();){
            Map<Position, Piece> pieceByPosition = new HashMap<>();
            putPieceByPosition(resultSet, pieceByPosition);
            return pieceByPosition;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void putPieceByPosition(ResultSet resultSet, Map<Position, Piece> pieceByPosition) throws SQLException {
        while (resultSet.next()) {
            String position = resultSet.getString(1);
            String rawPiece = resultSet.getString(2);

            pieceByPosition.put(Position.of(position),
                    PieceConverter.convert(rawPiece));
        }
    }

    public void save(Map<Position, Piece> pieces) {
        for (Map.Entry<Position, Piece> piece : pieces.entrySet()) {
            saveEachPiece(piece);
        }
    }

    public void removeAll() {
        final String deleteSql = "delete from piece";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);){
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void saveEachPiece(Map.Entry<Position, Piece> piece) {
        final String insertSql = "insert into piece(position, name) values (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql);) {
            preparedStatement.setString(1, piece.getKey().toString());
            preparedStatement.setString(2, piece.getValue().getName());
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
