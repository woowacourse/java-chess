package chess.dao;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.game.GameStatus;
import chess.dto.ChessPieceDTO;
import chess.dto.GameStatusDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.ServerInfo.*;

public class BoardDAO {
    private static BoardDAO instance = new BoardDAO();

    private BoardDAO() {
    }

    public static BoardDAO getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
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

    private Connection connectDriver() throws SQLException {
        try {
            String url = "jdbc:mysql://" + SERVER.getInfo() + "/" + DATABASE.getInfo() + OPTION.getInfo();
            return DriverManager.getConnection(url, USER_NAME.getInfo(), PASSWORD.getInfo());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connection Failed");
        }
    }

    public void initializeBoard() throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "truncate table board";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Initialize Board Failed");
        }
    }

    public void initializeGameStatus() throws SQLException {
        try {
            Connection connection = getConnection();
            String query = "truncate table game_status";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Initialize Game Status Failed");
        }

    }

    public List<ChessPieceDTO> loadBoard() throws SQLException {
        try (Connection connection = getConnection()) {
            List<ChessPieceDTO> chessPieces = new ArrayList<>();
            String query = "SELECT * FROM board";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            addChessPieceDTOToList(rs, chessPieces);
            pstmt.close();
            return chessPieces;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Board Load Failed");
        }
    }

    private void addChessPieceDTOToList(ResultSet rs, List<ChessPieceDTO> chessPieces) throws SQLException {
        while (rs.next()) {
            ChessPieceDTO chessPieceDTO = new ChessPieceDTO();

            chessPieceDTO.setPieceName(rs.getString("piece_name"));
            chessPieceDTO.setTeam(rs.getString("team"));
            chessPieceDTO.setX(rs.getInt("x_position"));
            chessPieceDTO.setY(rs.getInt("y_position"));
            chessPieces.add(chessPieceDTO);
        }
    }

    public GameStatusDTO loadGameStatus() throws SQLException {
        try (Connection connection = getConnection()) {
            GameStatusDTO gameStatusDTO = new GameStatusDTO();
            String query = "SELECT * FROM game_status";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            gameStatusDTO.setNowPlayingTeam(rs.getString("now_playing_team"));
            gameStatusDTO.setIsGameEnd(rs.getString("is_game_end"));
            pstmt.close();
            return gameStatusDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("GameStatus Load Failed");
        }
    }

    public void updateChessPiece(ChessPiece chessPiece, int i, int j) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, chessPiece.getName());
            pstmt.setString(2, chessPiece.getTeam().getTeamName());
            pstmt.setString(3, String.valueOf(i + 1));
            pstmt.setString(4, String.valueOf(j + 1));
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Update Chess Piece Failed");
        }
    }

    public void updateGameStatus(GameStatus gameStatus) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO game_status VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, gameStatus.getNowPlayingTeam().getTeamName());
            pstmt.setString(2, String.valueOf(gameStatus.isGameEnd()));
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Update Game Status Failed");
        }
    }
}
