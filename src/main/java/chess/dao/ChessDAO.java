package chess.dao;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.Turn;
import chess.domain.dto.BoardStatusDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceGenerator;
import chess.domain.piece.Team;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessDAO {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String DATABASE_NAME = "chess";
    private static final String SERVER_URL = "localhost:13306";

    private static ChessDAO instance;

    private ChessDAO() {
    }

    public static ChessDAO getInstance() {
        if (instance == null) {
            instance = new ChessDAO();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER_URL + "/" + DATABASE_NAME + OPTION, USERNAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    public void saveGame(BoardStatusDto boardStatusDto) throws SQLException {
        savePieces(boardStatusDto.getPieces());
        saveTurn(boardStatusDto.getTurn());
    }

    private void savePieces(Map<String, Piece> pieces) throws SQLException {
        clearPieces();
        String query = "INSERT INTO Pieces (position, representation, team) VALUES (?, ?, ?)";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            for (Piece piece : pieces.values()) {
                addEachPiece(statement, piece);
            }
            statement.executeBatch();
        }
    }

    private void addEachPiece(PreparedStatement pstmt, Piece piece) throws SQLException {
        pstmt.setString(1, piece.getPosition().toString());
        pstmt.setString(2, piece.toString());
        pstmt.setString(3, piece.getTeam().toString());
        pstmt.addBatch();
    }

    private void clearPieces() throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Pieces")
        ) {
            statement.executeUpdate();
        }
    }

    private void saveTurn(Turn turn) throws SQLException {
        String query = "INSERT INTO Turn (turn) VALUES (?) ON DUPLICATE KEY UPDATE turn=?";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            String team = turn.getTeam().toString();
            statement.setString(1, team);
            statement.setString(2, team);
            statement.executeUpdate();
        }
    }

    public Board loadGame() throws SQLException {
        return new Board(loadPieces(), loadTurn());
    }

    private Pieces loadPieces() throws SQLException {
        String query = "SELECT * FROM Pieces";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()
        ) {
            Map<Position, Piece> pieces = new HashMap<>();
            while (rs.next()) {
                Position position = new Position(rs.getString("position"));
                Piece piece = PieceGenerator.generate(
                        rs.getString("representation"),
                        rs.getString("team"),
                        rs.getString("position")
                );
                pieces.put(position, piece);
            }
            return new Pieces(pieces);
        }
    }

    private Turn loadTurn() throws SQLException {
        String query = "SELECT * FROM Turn";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()
        ) {
            if (!rs.next()) {
                return null;
            }
            return new Turn(Team.valueOf(rs.getString("turn")));
        }
    }
}
