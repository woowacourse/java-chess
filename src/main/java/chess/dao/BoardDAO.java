package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceConverter;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;



public class BoardDAO {

    public Board load() {
        final String sql = "select position, piece from board";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();){
            Map<Position, Piece> pieceByPosition = new HashMap<>();
            putPieceByPosition(resultSet, pieceByPosition);
            return new Board(() -> pieceByPosition);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void putPieceByPosition(ResultSet resultSet, Map<Position, Piece> pieceByPosition) throws SQLException {
        while (resultSet.next()) {
            String position = resultSet.getString(1);
            String rawPiece = resultSet.getString(2);
            String[] splitPiece = rawPiece.split("-");
            pieceByPosition.put(Position.of(position),
                    PieceConverter.convert(splitPiece[0], splitPiece[1]));
        }
    }

//    public void save(Board board) {
//        final String deleteSql = "delete * from board";
//        final String insertSql = "insert position, piece values (?, ?)"
//        try (
//                Connection connection = getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                ResultSet resultSet = preparedStatement.executeQuery();){
//            Map<Position, Piece> pieceByPosition = new HashMap<>();
//            putPieceByPosition(resultSet, pieceByPosition);
//            return new Board(() -> pieceByPosition);
//        } catch (SQLException e) {
//            throw new IllegalStateException(e.getMessage());
//        }
//    }

    private Connection getConnection() throws SQLException {
        final String password = "root";
        final String userName = "root";
        final String database = "chess";
        final String option = "?useSSL=false&serverTimezone=UTC";

        return DriverManager.getConnection("jdbc://localhost:13306/" + database + option,
                userName, password);
    }
}
