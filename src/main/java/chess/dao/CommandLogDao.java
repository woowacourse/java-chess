package chess.dao;

import chess.dbconnect.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandLogDao {

    private final Connector connector;

    public CommandLogDao(Connector connector) {
        this.connector = connector;
    }

    public void add(String command) throws SQLException {
        Connection connection = connector.getConnection();

        String query = "insert into commandlog (command) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, command);

        preparedStatement.executeUpdate();
        connector.closeConnection();
    }

    public List<String> getAllByOldOneFirst() throws SQLException {
        Connection connection = connector.getConnection();

        String query = "select command from commandlog order by execute_order asc";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> commands = new ArrayList<>();

        while (resultSet.next()) {
            commands.add(resultSet.getString("command"));
        }
        connector.closeConnection();
        return Collections.unmodifiableList(commands);
    }
}
