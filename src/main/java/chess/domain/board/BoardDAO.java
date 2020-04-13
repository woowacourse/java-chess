package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BoardDAO {
    private Connection connection;

    public BoardDAO() {
        this.connection = getConnection();
    }

    private Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chessDB"; // MySQL DATABASE 이름
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

    public void placePiece(final Board board, final Position position) throws SQLException {
        String query = "INSERT INTO board (position, piece) VALUES (?, ?) ON DUPLICATE KEY UPDATE position=?, piece=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, position.toString());
        preparedStatement.setString(2, board.findBy(position).getName());
        preparedStatement.setString(3, position.toString());
        preparedStatement.setString(4, board.findBy(position).getName());
        preparedStatement.executeUpdate();
    }

    public void deletePieces() throws SQLException {
        String query = "TRUNCATE board";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public Map<Position, Piece> findAllPieces() throws SQLException {
        String query = "SELECT * FROM board";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Position, Piece> output = new HashMap<>();
        while (resultSet.next()) {
            Piece piece = Piece.of(PieceType.valueOf(resultSet.getString("piece")));
            Position position = Position.of(resultSet.getString("position"));
            output.put(position, piece);
        }
        return output;
    }
//
//    public Optional<Piece> findPiece(Board board, Position position) throws SQLException {
//        String query = "SELECT * FROM board WHERE position = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, position.toString());
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (!resultSet.next()) return Optional.empty();
//
//        return Optional.of(board.findPieceBy(Position.of(resultSet.getString("position"))));
//    }
}

