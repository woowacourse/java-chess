package chess.dao;

import chess.RoundDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDao extends Dao {

    public List<RoundDto> selectRound() throws SQLException {
        String query = "SELECT round, start, target FROM game ORDER BY round ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<RoundDto> roundDtos = new ArrayList<>();

        while (resultSet.next()) {
            RoundDto roundDto = new RoundDto();
            roundDto.setRound(resultSet.getInt("round"));
            roundDto.setFrom(resultSet.getInt("start"));
            roundDto.setTo(resultSet.getInt("target"));
            roundDtos.add(roundDto);
        }

        return roundDtos;
    }

    public void insertRound(RoundDto roundDto) throws SQLException {
        String query = "INSERT INTO game (round, start, target) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roundDto.getRound());
        preparedStatement.setInt(2, roundDto.getFrom());
        preparedStatement.setInt(3, roundDto.getTo());
        preparedStatement.executeUpdate();
    }
}

