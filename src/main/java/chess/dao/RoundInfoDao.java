package chess.dao;

import chess.dao.utils.JdbcConnector;
import chess.service.dto.ResultDto;
import chess.service.dto.RoundInfoDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.sqls.RoundInfoSql.*;

public class RoundInfoDao {
    private RoundInfoDao() {
    }

    private static class RoundInfoDaoHolder {
        private static final RoundInfoDao INSTANCE = new RoundInfoDao();
    }

    public static RoundInfoDao getInstance() {
        return RoundInfoDaoHolder.INSTANCE;
    }

    public List<RoundInfoDto> selectAllGame(boolean isEnd) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GAME_BY_IS_END)) {
            preparedStatement.setBoolean(1, isEnd);

            // TODO: 2019-06-25 Close resultSet!!
            ResultSet resultSet = preparedStatement.executeQuery();

            List<RoundInfoDto> list = new ArrayList<>();
            while (resultSet.next()) {
                RoundInfoDto roundInfoDto = new RoundInfoDto();
                roundInfoDto.setRound(resultSet.getInt("id"));
                roundInfoDto.setWhitePlayer(resultSet.getString("white_player"));
                roundInfoDto.setBlackPlayer(resultSet.getString("black_player"));
                roundInfoDto.setEnd(resultSet.getBoolean("is_end"));

                list.add(roundInfoDto);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public int insertRoundInfo(String whitePlayer, String blackPlayer) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROUND_INFO, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, whitePlayer);
            preparedStatement.setString(2, blackPlayer);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLDataException();
            }

            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public int updateGameOver(int round) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, round, UPDATE_IS_END_TRUE)) {

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public RoundInfoDto selectRoundInfo(int round) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, round, SELECT_ROUND_INFO);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.next()) {
                throw new SQLDataException();
            }

            RoundInfoDto roundInfoDto = new RoundInfoDto();
            roundInfoDto.setRound(resultSet.getInt("id"));
            roundInfoDto.setWhitePlayer(resultSet.getString("white_player"));
            roundInfoDto.setBlackPlayer(resultSet.getString("black_player"));
            roundInfoDto.setEnd(resultSet.getBoolean("is_end"));

            return roundInfoDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public ResultDto selectGameResult(int round) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, round, SELECT_GAME_RESULT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.next()) {
                throw new SQLDataException();
            }

            ResultDto resultDto = new ResultDto();
            resultDto.setRound(resultSet.getInt("round_id"));
            resultDto.setWinner(resultSet.getString("name"));

            return resultDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, int round, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        return preparedStatement;
    }
}
