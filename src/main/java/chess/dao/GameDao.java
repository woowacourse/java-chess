package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.dto.BoardWebDto;
import chess.dto.GameStatusDto;
import chess.dto.RoomDto;
import chess.dto.RoomUsersDto;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class GameDao {

    private static final Gson GSON = new Gson();
    private final DataSource dataSource;

    public GameDao() {
        dataSource = dataSource();
    }

    private static DataSource dataSource() {
        String server = "localhost:13306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
        String userName = "root";
        String password = "root";
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:mysql://" + server + "/" + database + option);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    public String insertRoom(RoomDto roomDto) throws SQLException {
        String query = "INSERT INTO room (name, is_opened, white, black) VALUES(?, true, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, roomDto.getName());
            pstmt.setString(2, roomDto.getWhite());
            pstmt.setString(3, roomDto.getBlack());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys();) {
                rs.next();
                return rs.getString(1);
            }
        }
    }

    public void insertUser(String userName) throws SQLException {
        String query = "INSERT INTO users (name) "
            + "SELECT * FROM (SELECT ?) AS tmp "
            + "WHERE NOT EXISTS (SELECT * FROM users WHERE name = ?);";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
        }
    }

    public void insertBoardAndStatusDto(BoardWebDto boardWebDto, GameStatusDto gameStatusDto,
        String roomId) throws SQLException {
        String query = "INSERT INTO play_log (board, game_status, room_id) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, GSON.toJson(boardWebDto));
            pstmt.setString(2, GSON.toJson(gameStatusDto));
            pstmt.setString(3, roomId);
            pstmt.executeUpdate();
        }
    }

    private void generateFirstRow(String roomId) throws SQLException {
        Board board = new Board();
        BoardWebDto boardWebDto = new BoardWebDto(board);
        GameStatusDto gameStatusDto =
            new GameStatusDto(new ChessGame(board, new Turn(Team.WHITE)));
        insertBoardAndStatusDto(boardWebDto, gameStatusDto, roomId);
    }

    public RoomUsersDto roomUsers(String roomId) throws SQLException {
        String query = "SELECT\n"
            + "    white_user.name as whiteName,\n"
            + "    white_user.win as whiteWin,\n"
            + "    white_user.lose as whiteLose,\n"
            + "    black_user.name as blackName,\n"
            + "    black_user.win as blackWin,\n"
            + "    black_user.lose as blackLose\n"
            + "FROM \n"
            + "\troom AS r\n"
            + "    INNER JOIN users AS black_user ON r.black = black_user.name\n"
            + "    INNER JOIN users AS white_user ON r.white = white_user.name\n"
            + "WHERE r.id = ?;";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            try (ResultSet rs = pstmt.executeQuery();) {
                rs.next();
                return new RoomUsersDto(rs.getString("whiteName"),
                    rs.getString("whiteWin"),
                    rs.getString("whiteLose"),
                    rs.getString("blackName"),
                    rs.getString("blackWin"),
                    rs.getString("blackLose"));
            }
        }
    }

    public BoardWebDto latestBoard(String roomId) throws SQLException {
        String query = "SELECT board FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC LIMIT 1";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (!rs.next()) {
                    generateFirstRow(roomId);
                    return latestBoard(roomId);
                }
                String serializedBoard = rs.getString("board");
                return GSON.fromJson(serializedBoard, BoardWebDto.class);
            }
        }
    }

    public GameStatusDto latestGameStatus(String roomId) throws SQLException {
        String query = "SELECT game_status FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC LIMIT 1";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (!rs.next()) {
                    generateFirstRow(roomId);
                    return latestGameStatus(roomId);
                }
                String serializedStatus = rs.getString("game_status");
                return GSON.fromJson(serializedStatus, GameStatusDto.class);
            }
        }
    }

    public List<RoomDto> openedRooms() throws SQLException {
        String query = "SELECT id, name, white, black FROM room WHERE is_opened = true";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();) {
            List<RoomDto> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new RoomDto(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)));
            }
            return result;
        }
    }

    public void closeRoom(String roomId) throws SQLException {
        String query = "UPDATE room SET is_opened = false WHERE id = (?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }

    public void addUserStat(String roomId, Team winnerTeam) throws SQLException {
        String winner = "white";
        String loser = "black";
        if (winnerTeam.isBlack()) {
            winner = "black";
            loser = "white";
        }
        updateWinner(roomId, winner);
        updateLoser(roomId, loser);
    }

    private void updateWinner(String roomId, String winner) throws SQLException {
        String updateWinnerQueryForm = "UPDATE users\n"
            + "SET users.win = users.win + 1\n"
            + "WHERE users.name = (SELECT %s FROM room WHERE id = ?);";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection
                .prepareStatement(String.format(updateWinnerQueryForm, winner));) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }

    private void updateLoser(String roomId, String loser) throws SQLException {
        String updateLoserQuery = "UPDATE users\n"
            + "SET users.lose = users.lose + 1\n"
            + "WHERE users.name = (SELECT %s FROM room WHERE id = ?);";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection
                .prepareStatement(String.format(updateLoserQuery, loser));) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }
}
