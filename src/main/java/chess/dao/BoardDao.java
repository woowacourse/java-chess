package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public void save(Board board) {
        final Connection connection = getConnection();
        final String clearSql = "truncate table board";
        try {
            connection.prepareStatement(clearSql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final String sql = "insert into board (position, name) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final List<Piece> pieces = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                pieces.addAll(board.getRank(i).getPieces());
            }
            for (Piece piece : pieces) {
                statement.setString(1, piece.getPosition().getPosition());
                statement.setString(2, piece.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Board findBoard() {
        final Connection connection = getConnection();
        final String sql = "select position, name from board";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            final Map<Integer, Rank> ranks = new HashMap<>();
            final ArrayList<ArrayList<Piece>> l = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                l.add(new ArrayList<>());
            }

            while (resultSet.next()) {
                Position position = new Position(resultSet.getString("position"));
                l.get(position.getY())
                        .add(position.getX(), Piece.create(position, resultSet.getString("name")));
            }

            for (int i = 0; i < 8; i++) {
                ranks.put(i, new Rank(l.get(i)));
            }

            return new Board(ranks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() {
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
}
