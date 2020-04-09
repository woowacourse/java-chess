package chess.dao;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.ChessPieceCache;
import chess.domain.position.Position;
import chess.dto.ChessPositionDTO;

import java.sql.*;
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

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ConnectionDAO connectionDAO = new ConnectionDAO();

    private void closeResultSet() {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.err.println("ResultSet 오류:" + e.getMessage());
        }
    }

    private void closePrepareStatement() {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("preparedStatement 오류:" + e.getMessage());
        }
    }

    public void deleteBlackTable() {
        Connection connection = connectionDAO.getConnection();

        try {
            String dropQuery = "DELETE FROM black";
            preparedStatement = connection.prepareStatement(dropQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deleteBlackTable 오류:" + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public void insertInitialBlackPiece() {
        Connection connection = connectionDAO.getConnection();

        try {
            String setBlackPieceQuery = "INSERT INTO black VALUES " +
                    BLACK_INITIAL_BOARD;
            preparedStatement = connection.prepareStatement(setBlackPieceQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("insertBoard 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public void selectBlackBoard(Map<Position, ChessPiece> chessBoard) {
        Connection connection = connectionDAO.getConnection();

        try {
            String setBlackQuery = "SELECT * FROM black";
            preparedStatement = connection.prepareStatement(setBlackQuery);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chessBoard.put(Position.of(resultSet.getString(POSITION_COLUMN)),
                        ChessPieceCache.getChessPiece(resultSet.getString(CHESS_PIECE_COLUMN)
                                , resultSet.getString(POSITION_COLUMN)));
            }
        } catch (SQLException e) {
            System.err.println("selectBlackBoard 오류: " + e.getMessage());

        } finally {
            closeResultSet();
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public void updatePiece(ChessPositionDTO chessPositionDTO) {
        Connection connection = connectionDAO.getConnection();

        try {
            String query = "UPDATE black SET position = ? WHERE position = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, chessPositionDTO.getTargetPosition());
            preparedStatement.setString(2, chessPositionDTO.getSourcePosition());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("updatePiece 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public void deleteCaughtPiece(ChessPositionDTO chessPositionDTO) {
        Connection connection = connectionDAO.getConnection();

        try {
            String query = "DELETE  FROM black WHERE position = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, chessPositionDTO.getTargetPosition());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deleteCaughtPiece 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }
}
