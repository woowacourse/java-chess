package chess.domain.dao;

import chess.domain.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnDaoImpl implements TurnDao {

    private final DBConnection dbConnection = new DBConnection();

    @Override
    public void create(final Color color) {
        processQuery("INSERT INTO turn(current_color) VALUES (?)",
                preparedStatement -> {
                    preparedStatement.setString(1, color.name());
                    preparedStatement.execute();
                });
    }

    @Override
    public void update(final Color color) {
        String query = "UPDATE turn SET current_color = ?";
        processQuery(query, preparedStatement -> {
            preparedStatement.setString(1, color.name());
            preparedStatement.execute();
        });
    }

    @Override
    public Color getCurrentTurn() {
        String query = "SELECT * FROM turn";
        List<String> currentColors = new ArrayList<>();
        processQuery(query, preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            currentColors.add(rs.getString("current_color"));
        });
        return Color.valueOf(currentColors.get(0));
    }

    private void processQuery(String query, QueryProcessor queryProcessor) {
        try (final Connection connection = dbConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            queryProcessor.process(preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
