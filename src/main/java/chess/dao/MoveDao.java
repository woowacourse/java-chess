package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Game;
import chess.dto.MoveRequestDto;

public class MoveDao implements JdbcTemplateDao {
    public void addMove(final Game game, final MoveRequestDto moveRequestDto) throws SQLException {
        String query = "insert into move (game, start_position, end_position) values (?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, game.getId());
        statement.setString(2, moveRequestDto.getFrom());
        statement.setString(3, moveRequestDto.getTo());
        statement.executeUpdate();
        closeConnection(connection);
    }

    public List<MoveRequestDto> getMoves(final Game game) throws SQLException {
        String query = "select * from move where game = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, game.getId());
        List<MoveRequestDto> moves = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            moves.add(new MoveRequestDto(resultSet.getString("start_position"), resultSet.getString("end_position")));
        }
        closeConnection(connection);
        return moves;
    }

    public void reset(final Game game) throws SQLException {
        String query = "delete from move where game = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, game.getId());
        statement.executeUpdate();
        closeConnection(connection);
    }
}
