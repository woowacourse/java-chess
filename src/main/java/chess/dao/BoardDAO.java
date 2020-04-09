package chess.dao;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.game.GameStatus;
import chess.dto.ChessPieceDTO;
import chess.dto.GameStatusDTO;
import chess.service.GameService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.ServerInfo.*;

public class BoardDAO {
    private static BoardDAO instance = new BoardDAO();

    private BoardDAO(){
    }

    public static BoardDAO getInstance(){
        return instance;
    }

    public Connection getConnection() {
        loadDriver();
        return connectDriver();
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connectDriver() {
        Connection connection = null;

        try {
            String url = "jdbc:mysql://" + SERVER.getInfo() + "/" + DATABASE.getInfo() + OPTION.getInfo();
            connection = DriverManager.getConnection(url, USER_NAME.getInfo(), PASSWORD.getInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void initializeBoard() throws SQLException {
        Connection connection = getConnection();
        String query = "truncate table board";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    public void initializeGameStatus() throws SQLException {
        Connection connection = getConnection();
        String query = "truncate table game_status";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    public List<ChessPieceDTO> loadBoard() throws SQLException {
        List<ChessPieceDTO> chessPieces = new ArrayList<>();
        Connection connection = getConnection();
        String query = "SELECT * FROM board";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            ChessPieceDTO chessPieceDTO = new ChessPieceDTO();

            chessPieceDTO.setPieceName(rs.getString("piece_name"));
            chessPieceDTO.setTeam(rs.getString("team"));
            chessPieceDTO.setX(rs.getInt("x_position"));
            chessPieceDTO.setY(rs.getInt("y_position"));
            chessPieces.add(chessPieceDTO);
        }
        pstmt.close();
        closeConnection(connection);
        return chessPieces;
    }

    public GameStatusDTO loadGameStatus() throws SQLException {
        GameStatusDTO gameStatusDTO = new GameStatusDTO();
        Connection connection = getConnection();
        String query = "SELECT * FROM game_status";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        rs.next();
        gameStatusDTO.setNowPlayingTeam(rs.getString("now_playing_team"));
        gameStatusDTO.setIsGameEnd(rs.getString("is_game_end"));
        pstmt.close();
        closeConnection(connection);
        return gameStatusDTO;
    }

    public void updateChessPiece(ChessPiece chessPiece, int i, int j) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        if (!(chessPiece instanceof Blank)) {
            pstmt.setString(1, chessPiece.getName());
            pstmt.setString(2, chessPiece.getTeam().getTeamName());
            pstmt.setString(3, String.valueOf(i + 1));
            pstmt.setString(4, String.valueOf(j + 1));
            pstmt.executeUpdate();
        }
        pstmt.close();
        closeConnection(connection);
    }

    public void updateGameStatus(GameStatus gameStatus) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO game_status VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, gameStatus.getNowPlayingTeam().getTeamName());
        pstmt.setString(2, String.valueOf(gameStatus.isGameEnd()));
        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }
}
