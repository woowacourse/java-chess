package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ch.qos.logback.core.db.DBHelper.closeConnection;

public class SQLBoardDAO implements BoardDAO {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "db_name";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private static final String DESTINATION = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION);

    public SQLBoardDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
        }
    }

    @Override
    public void placePieceOn(Position position, Piece piece) throws SQLException {
        String query = "INSERT INTO board (position, piece) VALUES (?, ?) ON DUPLICATE KEY UPDATE position=?, piece=?";

        executeUpdate(query, ps -> {
            ps.setString(1, position.toString());
            ps.setString(2, piece.toString());
            ps.setString(3, position.toString());
            ps.setString(4, piece.toString());
        });
    }

    @Override
    public void placeInitialPieces() throws SQLException {
        for (Position position : Position.getAllPositions()) {
            removePieceOn(position);
        }

        for (Piece piece : Piece.getPieces()) {
            for (Position position : piece.initialPositions()) {
                placePieceOn(position, piece);
            }
        }
    }

    @Override
    public Optional<Piece> findPieceOn(Position position) throws SQLException {
        String query = "SELECT * FROM board WHERE position = ?";

        try (Connection con = DriverManager.getConnection(DESTINATION, USER_NAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, position.toString());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(Piece.of(rs.getString("piece")));
        }
    }

    @Override
    public Map<Position, Piece> findAllPieces() throws SQLException {
        String query = "SELECT * FROM board ";

        try (Connection con = DriverManager.getConnection(DESTINATION, USER_NAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            Map<Position, Piece> output = new HashMap<>();
            while (rs.next()) {
                Position position = Position.of(rs.getString("position"));
                Piece piece = Piece.of(rs.getString("piece"));

                output.put(position, piece);
            }

            return output;
        }
    }

    @Override
    public void removePieceOn(Position position) throws SQLException {
        String query = "DELETE FROM board WHERE position = ?";

        executeUpdate(query, (ps) -> {
            ps.setString(1, position.toString());
        });
    }

    private void executeUpdate(String query, throwingConsumer<PreparedStatement> consumer) throws SQLException {
        try (Connection con = DriverManager.getConnection(DESTINATION, USER_NAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            consumer.accept(ps);
            ps.executeUpdate();

            ps.close();
            closeConnection(con);
        }
    }

    private interface throwingConsumer<T> {
        void accept(T t) throws SQLException;
    }

}
