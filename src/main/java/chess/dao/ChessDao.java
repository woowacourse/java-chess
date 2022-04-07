package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.piece.ChessPiece;
import chess.domain.state.GameState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class ChessDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String TABLE_ID = "id";
    private static final String SQL_STATEMENT_EXCEPTION = "[ERROR] SQL 문을 실행할 수 없습니다.";
    private static final String DATA_NOT_EXISTS_EXCEPTION = "[ERROR] 요청된 데이터가 존재하지 않습니다.";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int saveGameState(GameState gameState) {
        final Connection connection = getConnection();
        final String sql = "insert into chessgame (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameState.getClass().getSimpleName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public void saveChessBoard(ChessBoard chessBoard) {
        int chessGameId = findChessGameId();
        Map<ChessBoardPosition, ChessPiece> board = chessBoard.getBoard();
        for (Entry<ChessBoardPosition, ChessPiece> boardBlock : board.entrySet()) {
            int chessPieceId = findChessPieceId(boardBlock.getValue());
            saveChessBoardPosition(boardBlock.getKey(), chessPieceId, chessGameId);
        }
    }

    private int findChessGameId() {
        final Connection connection = getConnection();
        final String sql = "select id from chessgame";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchElementException(DATA_NOT_EXISTS_EXCEPTION);
            }
            return resultSet.getInt(TABLE_ID);
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    private int findChessPieceId(ChessPiece chessPiece) {
        final Connection connection = getConnection();
        final String sql = "select id from chesspiece where name = ? and team = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessPiece.getClass().getSimpleName());
            statement.setString(2, chessPiece.getTeam().name());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchElementException(DATA_NOT_EXISTS_EXCEPTION);
            }
            return resultSet.getInt(TABLE_ID);
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    private void saveChessBoardPosition(ChessBoardPosition chessBoardPosition, int chessPieceId, int chessGameId) {
        final Connection connection = getConnection();
        final String sql = "insert into chessboard (board_row, board_column, chesspiece_id, chessgame_id) "
                + "values (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, chessBoardPosition.getRow());
            statement.setString(2, String.valueOf(chessBoardPosition.getColumn()));
            statement.setInt(3, chessPieceId);
            statement.setInt(4, chessGameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChessBoard() {
        final Connection connection = getConnection();
        final String sql = "delete from chessboard";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGameState() {
        final Connection connection = getConnection();
        final String sql = "delete from chessgame";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateChessBoard(int sourcePositionRow, char sourcePositionColumn,
                                 int targetPositionRow, char targetPositionColumn) {
        final Connection connection = getConnection();
        final String sql = "update chessboard set board_row = ?, board_column = ? "
                + "where board_row=? and board_column = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, targetPositionRow);
            statement.setString(2, String.valueOf(targetPositionColumn));
            statement.setInt(3, sourcePositionRow);
            statement.setString(4, String.valueOf(sourcePositionColumn));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChessPieceByPosition(int row, char column) {
        final Connection connection = getConnection();
        final String sql = "delete from chessboard where board_row= ? and board_column = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, row);
            statement.setString(2, String.valueOf(column));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGameState(GameState gameState) {
        final Connection connection = getConnection();
        final String sql = "update chessgame set state = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameState.getClass().getSimpleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
