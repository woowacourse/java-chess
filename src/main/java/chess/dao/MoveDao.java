package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Game;
import chess.domain.board.Path;
import chess.domain.board.Position;

public class MoveDao implements JdbcTemplateDao {

    public void addMove(final Game game, final Path path) throws SQLException {
        String query = "insert into move (game, start_position, end_position) values (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, game.getId());
            statement.setString(2, path.getStart());
            statement.setString(3, path.getEnd());
            statement.executeUpdate();
        }
    }

    public List<Path> getMoves(final Game game) throws SQLException {
        String query = "select * from move where game = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, game.getId());
            List<Path> moves = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String end = resultSet.getString("end_position");
                String start = resultSet.getString("start_position");
                moves.add(new Path(Position.of(start), Position.of(end)));
            }
            return moves;
        }
    }

    public void reset(final Game game) throws SQLException {
        String query = "delete from move where game = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, game.getId());
            statement.executeUpdate();
        }
    }
}
