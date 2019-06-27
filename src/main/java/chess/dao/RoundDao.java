package chess.dao;

import chess.dto.RoundDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDao {
    public void addRound(RoundDto roundDto) throws SQLException {
        PreparedStatementSetter pss = preparedStatement -> {
            preparedStatement.setInt(1, roundDto.getRound());
            preparedStatement.setInt(2, roundDto.getFrom());
            preparedStatement.setInt(3, roundDto.getTo());
        };
        JdbcTemplate template = new JdbcTemplate();
        String query = "INSERT INTO game (round, start, target) VALUES (?, ?, ?)";
        template.executeUpdate(query, pss);
    }

    public List<RoundDto> selectRound() throws SQLException {
        RowMapper rowMapper = resultSet -> {
            List<RoundDto> roundDtos = new ArrayList<>();
            while (resultSet.next()) {
                RoundDto roundDto = new RoundDto();
                roundDto.setRound(resultSet.getInt("round"));
                roundDto.setFrom(resultSet.getInt("start"));
                roundDto.setTo(resultSet.getInt("target"));
                roundDtos.add(roundDto);
            }
            return roundDtos;
        };
        JdbcTemplate template = new JdbcTemplate();
        String query = "SELECT round, start, target FROM game ORDER BY round ASC";
        return (List<RoundDto>) template.executeQuery(query, rowMapper);

    }
}