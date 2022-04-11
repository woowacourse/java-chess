package chess.dao;

import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
import chess.dto.TurnDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDAO implements GameDAO {

    private static final int NOT_EXIST_USER = 0;
    private static final String INIT_TURN = "blank";

    private final Connection connection;

    public ChessGameDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveGame(GameDTO gameDTO, TurnDTO turnDTO) {
        final String sql = "insert into game(white_user_name, black_user_name, turn) values (?, ?, ?)";

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, gameDTO.getWhiteUserName());
                statement.setString(2, gameDTO.getBlackUserName());
                statement.setString(3, turnDTO.getTurn());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int findGameIdByUser(GameDTO gameDTO) {
        final String sql = "select id from game where white_user_name= (?) and black_user_name = (?)";

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, gameDTO.getWhiteUserName());
                statement.setString(2, gameDTO.getBlackUserName());
                ResultSet resultSet = statement.executeQuery();
                return findId(resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NOT_EXIST_USER;
    }

    private int findId(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Integer.parseInt(resultSet.getString("id"));
        }
        return NOT_EXIST_USER;
    }

    @Override
    public void deleteGame(GameIdDTO gameIdDTO) {
        final String sql = "delete from game where id = (?)";

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, gameIdDTO.getId());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTurn(GameIdDTO gameIdDTO, String turn) {
        final String sql = "update game set turn = (?) where id = (?)";

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, turn);
                statement.setInt(2, gameIdDTO.getId());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String findTurn(GameIdDTO gameIdDTO) {
        final String sql = "select turn from game where id = (?)";

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, gameIdDTO.getId());
                ResultSet resultSet = statement.executeQuery();
                return findTurnName(resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return INIT_TURN;
    }

    private String findTurnName(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString("turn");
        }
        return INIT_TURN;
    }
}
