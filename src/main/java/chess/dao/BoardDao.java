package chess.dao;

import chess.domain.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.view.BoardDto;
import chess.view.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";


    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void saveBoard(final BoardDto boardDto) {
        final Connection connection = getConnection();
        final String insertLocationSql = "insert into board(location, team, name) values (?, ?, ?) "
                + "on duplicate key update location = ?, team = ?, name = ? ";

        try {
            Map<String, PieceDto> boardData = boardDto.getBoardData();
            final PreparedStatement insertStatement = connection.prepareStatement(insertLocationSql);
            for (String location : boardData.keySet()) {
                insertStatement.setString(1, location);
                insertStatement.setString(2, boardData.get(location).getTeam());
                insertStatement.setString(3, boardData.get(location).getName());
                insertStatement.setString(4, location);
                insertStatement.setString(5, boardData.get(location).getTeam());
                insertStatement.setString(6, boardData.get(location).getName());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Board getBoard() {
        final Connection connection = getConnection();
        final Map<Location, Piece> board = new LinkedHashMap<>();
        final String getBoardSql = "select * from board order by substr(location,2,1) desc, substr(location,1,1) asc";
        try {
            final PreparedStatement statement = connection.prepareStatement(getBoardSql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                board.put(Location.of(resultSet.getString("location")),
                        PieceFactory.of(resultSet.getString("team"), resultSet.getString("name"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Board(board);
    }

    public void removeBoard() {
        final Connection connection = getConnection();
        final String removeBoardSql = "delete from board";
        try {
            final PreparedStatement updateStatement = connection.prepareStatement(removeBoardSql);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
