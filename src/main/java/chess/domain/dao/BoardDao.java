package chess.domain.dao;

import chess.domain.dto.PieceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao{

    private static final int EMPTY_RESULT = 0;
    private final Connection connection;
    private int boardId = 0;
    private final Connector connector;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BoardDao(Connector connector) {
        this.connector = connector;
        connection = this.connector.makeConnection(Connector.PROD_DB_URL);
    }

    BoardDao(final Connection connection, Connector connector) {
        this.connection = connection;
        this.connector = connector;
    }

    public void save(int gameId, String position, String piece, String color) {
        final String sql = "insert into Board (id, game_id, position, piece, color) values(?, ?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = makeSaveStatement(gameId, position, piece, color, sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    private PreparedStatement makeSaveStatement(int gameId, String position, String piece, String color, String sql) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ++boardId);
        statement.setInt(2, gameId);
        statement.setString(3, position);
        statement.setString(4, piece);
        statement.setString(5, color);
        return statement;
    }

    public List<PieceDto> findByGameId(int gameId) {
        final String sql = "select * from board where game_id = ?";
        ResultSet result;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameId);
            result = statement.executeQuery();
            return makePieceList(result);
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    private List<PieceDto> makePieceList(ResultSet result) throws SQLException {
        List<PieceDto> pieces = new ArrayList<>();
        while (result.next()) {
            pieces.add(
                    new PieceDto(
                            result.getInt("game_id"),
                            result.getString("position"),
                            result.getString("piece"),
                            result.getString("color")));
        }
        result.close();
        return pieces;
    }

    public void delete(int gameId) {
        if (isSavedGameExist(gameId)) {
            return;
        }
        runDelete(gameId);
    }

    private void runDelete(int gameId) {
        final String sql = "delete from Board where game_id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new IllegalArgumentException("요청이 정상적으로 실행되지 않았습니다.");
        }
    }

    private boolean isSavedGameExist(int gameId) {
        return gameId == EMPTY_RESULT;
    }
}
