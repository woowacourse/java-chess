package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SQLBoardDAO implements BoardDAO {
    private Connection connection;

    // 나중에 수정
    public SQLBoardDAO() {
        this.connection = getConnection();
    }

    private Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    private void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    @Override
    public void placePieceOn(Position position, Piece piece) throws SQLException {
        String query = "INSERT INTO board (position, piece) VALUES (?, ?) ON DUPLICATE KEY UPDATE position=?, piece=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, position.toString());
        preparedStatement.setString(2, piece.toString());
        preparedStatement.setString(3, position.toString());
        preparedStatement.setString(4, piece.toString());
        preparedStatement.executeUpdate();
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
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, position.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return Optional.empty();

        return Optional.of(Piece.of(resultSet.getString("piece")));
    }

    @Override
    public Map<Position, Piece> findAllPieces() throws SQLException {
        String query = "SELECT * FROM board ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Position, Piece> output = new HashMap<>();
        while (resultSet.next()) {
            Position position = Position.of(resultSet.getString("position"));
            Piece piece = Piece.of(resultSet.getString("piece"));

            output.put(position, piece);
        }

        return output;
    }

    @Override
    public void removePieceOn(Position position) throws SQLException {
        String query = "DELETE FROM board WHERE position = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, position.toString());
        preparedStatement.executeUpdate();
    }
}
