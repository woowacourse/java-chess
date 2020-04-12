package chess.dao;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.ChessPieceCache;
import chess.domain.position.Position;
import chess.dto.ChessPositionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BlackPieceDAO {

    private static final String POSITION_COLUMN = "position";
    private static final String CHESS_PIECE_COLUMN = "chessPiece";
    private static final String BLACK_INITIAL_BOARD = "('a7','p')," +
            "('b7','p')," +
            "('c7','p')," +
            "('d7','p')," +
            "('e7','p')," +
            "('f7','p')," +
            "('g7','p')," +
            "('h7','p')," +
            "('a8','r')," +
            "('b8','n')," +
            "('c8','b')," +
            "('d8','q')," +
            "('e8','k')," +
            "('f8','b')," +
            "('g8','n')," +
            "('h8','r')";

    public void deleteBlackTable() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM black")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deleteBlackTable 오류:" + e.getMessage());
        }
    }

    public void insertInitialBlackPiece() {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO black VALUES "
                     + BLACK_INITIAL_BOARD)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("insertBoard 오류: " + e.getMessage());
        }
    }

    public void selectBlackBoard(Map<Position, ChessPiece> chessBoard) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM black");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                chessBoard.put(Position.of(resultSet.getString(POSITION_COLUMN)),
                        ChessPieceCache.getChessPiece(resultSet.getString(CHESS_PIECE_COLUMN)
                                , resultSet.getString(POSITION_COLUMN)));
            }
        } catch (SQLException e) {
            throw new SQLExecuteException("selectBlackBoard 오류: " + e.getMessage());
        }
    }

    public void updatePiece(ChessPositionDTO chessPositionDTO) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE black SET position = ? WHERE position = ?")) {
            preparedStatement.setString(1, chessPositionDTO.getTargetPosition());
            preparedStatement.setString(2, chessPositionDTO.getSourcePosition());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLExecuteException("updatePiece 오류: " + e.getMessage());
        }
    }

    public void deleteCaughtPiece(ChessPositionDTO chessPositionDTO) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE  FROM black WHERE position = ?")) {
            preparedStatement.setString(1, chessPositionDTO.getTargetPosition());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deleteCaughtPiece 오류: " + e.getMessage());
        }
    }
}
