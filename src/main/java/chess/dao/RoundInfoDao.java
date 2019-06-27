package chess.dao;

import chess.dao.exception.DataAccessException;
import chess.dao.utils.JdbcConnector;
import chess.service.dto.ResultDto;
import chess.service.dto.RoundInfoDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.sqls.Columns.*;
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

    public List<RoundInfoDto> selectAllGame(boolean isEnd) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatementWithIsEnd(connection, isEnd);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<RoundInfoDto> list = new ArrayList<>();
            while (resultSet.next()) {
                RoundInfoDto roundInfoDto = new RoundInfoDto();
                roundInfoDto.setRound(resultSet.getInt(ID));
                roundInfoDto.setWhitePlayer(resultSet.getString(WHITE_PLAYER));
                roundInfoDto.setBlackPlayer(resultSet.getString(BLACK_PLAYER));
                roundInfoDto.setEnd(resultSet.getBoolean(IS_END));
                list.add(roundInfoDto);
            }
            return list;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private PreparedStatement createPreparedStatementWithIsEnd(Connection connection, boolean isEnd) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GAME_BY_IS_END);
        preparedStatement.setBoolean(1, isEnd);
        return preparedStatement;
    }

    public int insertRoundInfo(String whitePlayer, String blackPlayer) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatementWithPlayers(whitePlayer, blackPlayer, connection);
             ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            if (!resultSet.next()) {
                throw new DataAccessException("round 입력 실패!");
            }

            return resultSet.getInt(1);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private PreparedStatement createPreparedStatementWithPlayers(String whitePlayer, String blackPlayer, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROUND_INFO, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, whitePlayer);
        preparedStatement.setString(2, blackPlayer);
        preparedStatement.executeUpdate();
        return preparedStatement;
    }

    public int updateGameOver(int round) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatementWithRound(connection, round, UPDATE_IS_END_TRUE)) {

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public RoundInfoDto selectRoundInfo(int round) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatementWithRound(connection, round, SELECT_ROUND_INFO);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.next()) {
                throw new DataAccessException("게임 정보를 불러오지 못했습니다.");
            }

            RoundInfoDto roundInfoDto = new RoundInfoDto();
            roundInfoDto.setRound(resultSet.getInt(ID));
            roundInfoDto.setWhitePlayer(resultSet.getString(WHITE_PLAYER));
            roundInfoDto.setBlackPlayer(resultSet.getString(BLACK_PLAYER));
            roundInfoDto.setEnd(resultSet.getBoolean(IS_END));

            return roundInfoDto;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public ResultDto selectGameResult(int round) {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = createPreparedStatementWithRound(connection, round, SELECT_GAME_RESULT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.next()) {
                throw new DataAccessException("게임 결과를 불러오지 못했습니다.");
            }

            ResultDto resultDto = new ResultDto();
            resultDto.setRound(resultSet.getInt(ROUND_ID));
            resultDto.setWinner(resultSet.getString(NAME));

            return resultDto;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    private PreparedStatement createPreparedStatementWithRound(Connection connection, int round, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        return preparedStatement;
    }
}
