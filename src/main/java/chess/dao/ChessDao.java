package chess.dao;

import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

final public class ChessDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
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

    public void addGame(String gameId, ChessGame game) {
        if (isExistingGame(gameId)) {
            throw new IllegalArgumentException("이미 존재하는 게임입니다.");
        }
        String query = "INSERT INTO games VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, gameId);
            pstmt.setString(2, stringifiedBoard(game.board()));
            pstmt.setString(3, stringifiedTurn(game.currentTurn()));
            pstmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    private String stringifiedBoard(Map<Position, Piece> board) {
        return board.values()
                .stream()
                .map(Piece::name)
                .collect(Collectors.joining(","));
    }

    private String stringifiedTurn(Team team) {
        return team.teamName();
    }

    public boolean isExistingGame(String gameId) {
        String query = "SELECT * FROM games WHERE game_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    public ChessGame findGameById(String gameId) {
        String query = "SELECT * FROM games WHERE game_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalArgumentException("존재하지 않는 게임입니다.");
                }
                return new ChessGame(rs.getString("pieces"), rs.getString("turn"));
            }
        } catch (SQLException throwables) {
            return null;
        }
    }

    public void deleteGameById(String gameId) {
        String query = "DELETE FROM games WHERE game_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void updateGame(String gameId, ChessGame game) {
        String query = "UPDATE games SET pieces = ? , turn = ? WHERE game_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, stringifiedBoard(game.board()));
            pstmt.setString(2, stringifiedTurn(game.currentTurn()));
            pstmt.setString(3, gameId);
            pstmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }
}
