package chess.dao;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.ChessPieceCache;
import chess.domain.position.Position;
import chess.dto.ChessPositionDTO;

import java.sql.*;
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

    private ConnectionDAO connectionDAO = new ConnectionDAO();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    private void closePrepareStatement() {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("preparedStatement 오류:" + e.getMessage());
        }
    }

    private void closeResultSet() {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.err.println("ResultSet 오류:" + e.getMessage());
        }
    }

    public void deleteWhiteTable() {
        Connection connection = connectionDAO.getConnection();

        try {
            String dropQuery = "DELETE FROM white";
            preparedStatement = connection.prepareStatement(dropQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deleteWhiteTable 오류:" + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }


    public void insertInitialWhitePiece() {
        Connection connection = connectionDAO.getConnection();

        try {
            String setWhitePieceQuery = "INSERT INTO white VALUES " +
                    WHITE_INITIAL_BOARD;
            preparedStatement = connection.prepareStatement(setWhitePieceQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insertBoard 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }

    }

    public void selectWhiteBoard(Map<Position, ChessPiece> chessBoard) {
        Connection connection = connectionDAO.getConnection();
        try {
            String setWhiteQuery = "SELECT * FROM white";
            preparedStatement = connection.prepareStatement(setWhiteQuery);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chessBoard.put(Position.of(resultSet.getString(POSITION_COLUMN)),
                        ChessPieceCache.getChessPiece(resultSet.getString(CHESS_PIECE_COLUMN)
                                , resultSet.getString(POSITION_COLUMN)));
            }
        } catch (SQLException e) {
            System.err.println("selectWhiteBoard 오류: " + e.getMessage());

        } finally {
            closeResultSet();
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public void updatePiece(ChessPositionDTO chessPositionDTO) {
        Connection connection = connectionDAO.getConnection();
        try {
            String query = "UPDATE white SET position = ? WHERE position = ?";
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
            String query = "DELETE  FROM white WHERE position = ?";
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
