package chess.domain.dao;

import chess.domain.dto.GameDto;
import chess.domain.game.Status;
import chess.domain.game.board.ChessBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GameDao {

    private static final int EMPTY_RESULT = 0;
    private final Connection connection;
    private int gameId = 0;
    private final Connector connector;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GameDao(Connector connector) {
        this.connector = connector;
        connection = this.connector.makeConnection(Connector.PROD_DB_URL);
    }

    public GameDao(final Connection connection, Connector connector) {
        this.connection = connection;
        this.connector = connector;
    }

    public int save(ChessBoard chessBoard) {
        final String sql = "insert into Game (id, status, turn) values(?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = makeSaveStatements(chessBoard, sql);
            statement.executeUpdate();
            return gameId++;
        } catch (Exception throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    private PreparedStatement makeSaveStatements(ChessBoard chessBoard, String sql) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, ++gameId);
            statement.setBoolean(2, chessBoard.compareStatus(Status.PLAYING));
            statement.setString(3, chessBoard.getCurrentTurn().name());
            return statement;
        } catch (Exception throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    public int findLastGameId() {
        final String sql = "SELECT * FROM Game ORDER BY id DESC LIMIT 1";
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            result = statement.executeQuery();
            if (!result.next()) {
                return EMPTY_RESULT;
            }
            return result.getInt("id");
        } catch (Exception throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    public GameDto findById(int id) {
        final String sql = "select id, status, turn from game where id = ?";
        PreparedStatement statement;
        ResultSet result;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new GameDto(
                    result.getInt("id"),
                    result.getBoolean("status"),
                    result.getString("turn")
            );
        } catch (Exception throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    public int delete() {
        final String sql = "delete from game where id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int lastGameid = findLastGameId();
            statement.setInt(1, lastGameid);
            statement.executeUpdate();
            return lastGameid;
        } catch (Exception throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }
}
