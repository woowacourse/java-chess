package chess.domain.dao;

import chess.domain.dto.PieceDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    private static final int EMPTY = 0;
    private final Connection connection;
    private int id = 0;
    private Connector connector = new Connector();

    public BoardDao() {
        connection = connector.makeConnection();
    }

    BoardDao(final Connection connection) {
        this.connection = connection;
    }

    public void save(int gameId, String position, String piece, String color) {
        final String sql = "insert into Board (id, game_id, position, piece, color) values(?, ?, ?, ?, ?)";
        try {
            final PreparedStatement statement = makeSaveStatement(gameId, position, piece, color, sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private PreparedStatement makeSaveStatement(int gameId, String position, String piece, String color, String sql) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ++id);
        statement.setInt(2, gameId);
        statement.setString(3, position);
        statement.setString(4, piece);
        statement.setString(5, color);
        return statement;
    }

    public List<PieceDto> findByGameId(int gameId) {
        final String sql = "select * from board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameId);
            final ResultSet result = statement.executeQuery();
            return makePieceList(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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
        return pieces;
    }

    public void delete(int gameId) {
        if (isSavedGameExist(gameId)) {
            return;
        }

        final String sql = "delete from Board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isSavedGameExist(int gameId) {
        return gameId == EMPTY;
    }
}
