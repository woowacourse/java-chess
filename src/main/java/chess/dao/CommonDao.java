package chess.dao;

import chess.domain.GameState;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.factory.PieceFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CommonDao {

    private static final int COLUMN = 0;
    private static final int ROW = 1;

    private static final String URL = "jdbc:mysql://localhost:3308/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    static void CreateUpdateDelete(final String sql, StatementMaker<PreparedStatement> statementConsumer) {
        try {
            final Connection connection = getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementConsumer.makeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    static int findId(final String sql, final StatementMaker<PreparedStatement> statementMaker,
                      final String columnLabel) {
        try {
            final Connection connection = getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementMaker.makeStatement(statement);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new NoSuchElementException("해당 레코드가 존재하지 않습니다.");
            }
            return resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    static GameState getGameStatus(final String sql, final StatementMaker<PreparedStatement> statementMaker) {
        try {
            final Connection connection = CommonDao.getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementMaker.makeStatement(statement);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException();
            }
            final String gameStateName = resultSet.getString("game_status");
            return GameState.valueOf(gameStateName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }


    static Map<Position, Piece> getPieces(final String sql, final StatementMaker<PreparedStatement> statementMaker) {
        try {
            final Connection connection = CommonDao.getConnection();
            final PreparedStatement statement = connection.prepareStatement(sql);
            statementMaker.makeStatement(statement);
            final ResultSet resultSet = statement.executeQuery();
            final Map<Position, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                final Position position = createPosition(resultSet);
                final Piece piece = createPiece(resultSet);
                pieces.put(position, piece);
            }
            return pieces;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    private static Piece createPiece(final ResultSet resultSet) throws SQLException {
        final String symbol = resultSet.getString("symbol");
        final String color = resultSet.getString("color");
        return PieceFactory.createPiece(PieceType.of(symbol), Color.valueOf(color));
    }

    private static Position createPosition(final ResultSet resultSet) throws SQLException {
        final String rawPosition = resultSet.getString("position");
        return new Position(Column.from(rawPosition.substring(COLUMN, 1)),
                Row.from(Integer.parseInt(rawPosition.substring(ROW, 2))));
    }

    private CommonDao() {
    }
}
