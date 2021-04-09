package chess.dao;

import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChessDaoTest {
    private final String gameId = "new_game";
    private final ChessGame game = new ChessGame();
    private final TestDao chessDao = new TestDao();

    @BeforeEach
    public void setup() throws SQLException {
        chessDao.addGame(gameId, new ChessGame());
    }

    @AfterEach
    void tearDown() throws SQLException {
        chessDao.deleteGameById(gameId);
    }

    @Test
    @DisplayName("db 연결 기능")
    public void connection() {
        Connection con = chessDao.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("게임 가져오는 기능")
    void findGame() throws SQLException {
        ChessGame gameFromData = chessDao.findGameById(gameId);
        assertThat(chessDao.stringifiedBoard(game.board())).isEqualTo(chessDao.stringifiedBoard(gameFromData.board()));
        assertThat(chessDao.stringifiedTurn(game.currentTurn())).isEqualTo(chessDao.stringifiedTurn(gameFromData.currentTurn()));
    }

    @Test
    @DisplayName("게임 상태 업데이트 기능")
    void updateGame() throws SQLException {
        game.move(new Position("b2"), new Position("b3"));
        chessDao.updateGame(gameId, game);
        ChessGame gameFromData = chessDao.findGameById(gameId);
        assertThat(chessDao.stringifiedBoard(game.board())).isEqualTo(chessDao.stringifiedBoard(gameFromData.board()));
        assertThat(chessDao.stringifiedTurn(game.currentTurn())).isEqualTo(chessDao.stringifiedTurn(gameFromData.currentTurn()));
    }
}

final class TestDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "test"; // MySQL DATABASE 이름
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

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addGame(String gameId, ChessGame game) throws SQLException {
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

    String stringifiedBoard(Map<Position, Piece> board) {
        return board.values()
                .stream()
                .map(Piece::name)
                .collect(Collectors.joining(","));
    }

    String stringifiedTurn(Team team) {
        return team.teamName();
    }

    public boolean isExistingGame(String gameId) throws SQLException {
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

    public void deleteGameById(String gameId) throws SQLException {
        String query = "DELETE FROM games WHERE game_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void updateGame(String gameId, ChessGame game) throws SQLException {
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
