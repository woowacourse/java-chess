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

public class WhitePieceDAO {

    private static final String POSITION_COLUMN = "position";
    private static final String CHESS_PIECE_COLUMN = "chessPiece";
    private static final String WHITE_INITIAL_BOARD = "('a2','P')," +
            "('b2','P')," +
            "('c2','P')," +
            "('d2','P')," +
            "('e2','P')," +
            "('f2','P')," +
            "('g2','P')," +
            "('h2','P')," +
            "('a1','R')," +
            "('b1','N')," +
            "('c1','B')," +
            "('d1','Q')," +
            "('e1','K')," +
            "('f1','B')," +
            "('g1','N')," +
            "('h1','R')";

    public void deleteWhiteTable() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM white");) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteWhiteTable 오류:" + e.getMessage());
        }
    }

    public void insertInitialWhitePiece() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO white VALUES " + WHITE_INITIAL_BOARD)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insertBoard 오류: " + e.getMessage());
        }
    }

    public void selectWhiteBoard(Map<Position, ChessPiece> chessBoard) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM white");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                chessBoard.put(Position.of(resultSet.getString(POSITION_COLUMN)),
                        ChessPieceCache.getChessPiece(resultSet.getString(CHESS_PIECE_COLUMN)
                                , resultSet.getString(POSITION_COLUMN)));
            }
        } catch (SQLException e) {
            System.err.println("selectWhiteBoard 오류: " + e.getMessage());
        }
    }

    public void updatePiece(ChessPositionDTO chessPositionDTO) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE white SET position = ? WHERE position = ?")) {
            preparedStatement.setString(1, chessPositionDTO.getTargetPosition());
            preparedStatement.setString(2, chessPositionDTO.getSourcePosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updatePiece 오류: " + e.getMessage());
        }
    }

    public void deleteCaughtPiece(ChessPositionDTO chessPositionDTO) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE  FROM white WHERE position = ?");
        ) {
            preparedStatement.setString(1, chessPositionDTO.getTargetPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteCaughtPiece 오류: " + e.getMessage());
        }
    }
}
