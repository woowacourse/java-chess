package chess.dao;

import chess.domain.ChessBoardPosition;
import chess.domain.piece.ChessPiece;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class ChessPieceDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String TABLE_ID = "id";
    private static final String CHESSBOARD_BOARD_ROW = "board_row";
    private static final String CHESSBOARD_BOARD_COLUMN = "board_column";
    private static final String CHESSPIECE_NAME = "name";
    private static final String CHESSPIECE_TEAM = "team";
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

    public void saveAll(int chessGameId, Map<ChessBoardPosition, ChessPiece> chessPieces) {
        for (Entry<ChessBoardPosition, ChessPiece> boardBlock : chessPieces.entrySet()) {
            int chessPieceId = findId(boardBlock.getValue());
            save(boardBlock.getKey(), chessPieceId, chessGameId);
        }
    }

    private int findId(ChessPiece chessPiece) {
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

    private void save(ChessBoardPosition chessBoardPosition, int chessPieceId, int chessGameId) {
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

    public Map<String, List<String>> findAll() {
        final Connection connection = getConnection();
        final String sql = "select board_row, board_column, name, team "
                + "from chessboard join chesspiece on chessboard.chesspiece_id = chesspiece.id";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Map<String, List<String>> board = new HashMap<>();
            while (resultSet.next()) {
                int row = resultSet.getInt(CHESSBOARD_BOARD_ROW);
                String column = resultSet.getString(CHESSBOARD_BOARD_COLUMN);
                String chessPieceName = resultSet.getString(CHESSPIECE_NAME);
                String team = resultSet.getString(CHESSPIECE_TEAM);
                board.put(column + row, List.of(chessPieceName, team));
            }
            return board;
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public void update(int sourcePositionRow, char sourcePositionColumn,
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

    public void deleteAll() {
        final Connection connection = getConnection();
        final String sql = "delete from chessboard";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPosition(int row, char column) {
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
}
