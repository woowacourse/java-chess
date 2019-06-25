package chess.dao;

import chess.dao.utils.JdbcConnector;
import chess.dto.RoundInfoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
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

    public List<RoundInfoDto> selectAllUnfinishedGame() throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_UNFINISHED_GAME);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<RoundInfoDto> list = new ArrayList<>();
            while (resultSet.next()) {
                RoundInfoDto roundInfoDTO = new RoundInfoDto();
                roundInfoDTO.setRound(resultSet.getInt("id"));
                roundInfoDTO.setWhitePlayer(resultSet.getString("white_player"));
                roundInfoDTO.setBlackPlayer(resultSet.getString("black_player"));
                roundInfoDTO.setEnd(resultSet.getBoolean("is_end"));

                list.add(roundInfoDTO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public int insertRoundInfo(String whitePlayer, String blackPlayer) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROUND_INFO)) {

            preparedStatement.setString(1, whitePlayer);
            preparedStatement.setString(2, blackPlayer);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    public int updateGameOver(int round) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IS_END_TRUE)) {
            preparedStatement.setInt(1, round);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }
}
