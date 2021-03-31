package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.dto.BoardWebDto;
import chess.dto.GameStatusDto;
import chess.dto.RoomDto;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    private static final String INSERT_BOARD_AND_STATUS_QUERY = "INSERT INTO play_log (board, game_status, room_id) VALUES (?, ?, ?)";
    private static final String SELECT_BOARD_QUERY = "SELECT board FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC LIMIT 1";
    private static final String SELECT_STATUS_QUERY = "SELECT game_status FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC LIMIT 1";
    private static final String SELECT_OPENED_ROOMS_QUERY = "SELECT id, name FROM room WHERE is_opened = true";

    private static final Gson GSON = new Gson();

    private Connection connection() {
        Connection connection = null;
        String server = "localhost:13306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void insertBoardAndStatusDto(BoardWebDto boardWebDto, GameStatusDto gameStatusDto, String roomId)
        throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(INSERT_BOARD_AND_STATUS_QUERY);
        pstmt.setString(1, GSON.toJson(boardWebDto));
        pstmt.setString(2, GSON.toJson(gameStatusDto));
        pstmt.setString(3, roomId);
        pstmt.executeUpdate();
        closeConnection(connection);
    }

    private void generateFirstRow(String roomId) throws SQLException {
        Board board = new Board();
        BoardWebDto boardWebDto = new BoardWebDto(board);
        GameStatusDto gameStatusDto =
            new GameStatusDto(new ChessGame(board, new Turn(Team.WHITE)));
        insertBoardAndStatusDto(boardWebDto, gameStatusDto, roomId);
    }

    public BoardWebDto latestBoard(String roomId) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(SELECT_BOARD_QUERY);
        pstmt.setString(1, roomId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            generateFirstRow(roomId);
            return latestBoard(roomId);
        }
        String serializedBoard = rs.getString("board");
        connection.close();
        return GSON.fromJson(serializedBoard, BoardWebDto.class);
    }

    public GameStatusDto latestGameStatus(String roomId) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(SELECT_STATUS_QUERY);
        pstmt.setString(1, roomId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            generateFirstRow(roomId);
            return latestGameStatus(roomId);
        }
        String serializedStatus = rs.getString("game_status");
        connection.close();
        return GSON.fromJson(serializedStatus, GameStatusDto.class);
    }

    public List<RoomDto> openedRooms() throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(SELECT_OPENED_ROOMS_QUERY);
        ResultSet rs = pstmt.executeQuery();
        List<RoomDto> result = new ArrayList<>();

        while (rs.next()) {
            result.add(new RoomDto(rs.getString(1), rs.getString(2)));
        }

        return result;
    }
}
