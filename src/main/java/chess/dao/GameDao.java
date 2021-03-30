package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.dto.BoardWebDto;
import chess.dto.GameStatusDto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private static final String INSERT_BOARD_AND_STATUS_QUERY = "INSERT INTO chess (serialized_board, serialized_status) VALUES (?, ?)";
    private static final String SELECT_BOARD_QUERY = "SELECT serialized_board FROM chess ORDER BY id DESC LIMIT 1";
    public static final String SELECT_STATUS_QUERY = "SELECT serialized_status FROM chess ORDER BY id DESC LIMIT 1";

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

    private byte[] serializedObject(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        return baos.toByteArray();
    }

    public void addSerializedBoardAndStatus(BoardWebDto boardWebDto, GameStatusDto gameStatusDto)
        throws IOException, SQLException {
        byte[] serializedBoard = serializedObject(boardWebDto);
        byte[] serializedGameStatus = serializedObject(gameStatusDto);
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(INSERT_BOARD_AND_STATUS_QUERY);
        pstmt.setBytes(1, serializedBoard);
        pstmt.setBytes(2, serializedGameStatus);
        pstmt.executeUpdate();
        closeConnection(connection);
    }

    private void generateFirstRow() throws IOException, SQLException {
        Board board = new Board();
        BoardWebDto boardWebDto = new BoardWebDto(board);
        GameStatusDto gameStatusDto =
            new GameStatusDto(new ChessGame(board, new Turn(Team.WHITE)));
        addSerializedBoardAndStatus(boardWebDto, gameStatusDto);
    }

    private Object deserializedObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

    public BoardWebDto latestBoard() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(SELECT_BOARD_QUERY);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            generateFirstRow();
            return latestBoard();
        }
        byte[] serializedBoard = rs.getBytes("serialized_board");
        connection.close();
        return (BoardWebDto) deserializedObject(serializedBoard);
    }

    public GameStatusDto latestGameStatus() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = connection();
        PreparedStatement pstmt = connection.prepareStatement(SELECT_STATUS_QUERY);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            generateFirstRow();
            return latestGameStatus();
        }
        byte[] serializedStatus = rs.getBytes("serialized_status");
        connection.close();
        return (GameStatusDto) deserializedObject(serializedStatus);
    }
}
