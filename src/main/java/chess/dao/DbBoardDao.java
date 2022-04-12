package chess.dao;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.view.ChessPieceName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class DbBoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String UNVALID_CHESSPIECE_TYPE_EXCEPTION = "[ERROR] 유효하지 않은 체스피스 타입입니다.";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAll(int gameId, Map<ChessBoardPosition, ChessPiece> mapData) {
        deleteAll(gameId);
        saveAll(gameId, mapData);
    }

    public void deleteAll(int gameId) {
        final Connection connection = getConnection();
        final String sql = "delete from board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void saveAll(int gameId, Map<ChessBoardPosition, ChessPiece> mapInformation) {
        for (Entry<ChessBoardPosition, ChessPiece> entry : mapInformation.entrySet()) {
            save(gameId, entry.getKey(), entry.getValue());
        }
    }

    private void save(int gameId, ChessBoardPosition chessBoardPosition, ChessPiece chessPiece) {
        final Connection connection = getConnection();
        final String sql = "insert into board (game_id, type, y, x) values (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.setString(2, ChessPieceName.of(chessPiece));
            statement.setInt(3, chessBoardPosition.getColumn());
            statement.setInt(4, chessBoardPosition.getRow());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Map<ChessBoardPosition, ChessPiece> findAll(int gameId) {
        Map<ChessBoardPosition, ChessPiece> mapInfo = new HashMap<>();
        final Connection connection = getConnection();
        final String sql = "select * from board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mapInfo.put(ChessBoardPosition.of(resultSet.getInt("y"), resultSet.getInt("x"))
                        , makeProperChessPiece(resultSet.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return mapInfo;
    }

    private ChessPiece makeProperChessPiece(String type) {
        if (Objects.equals(type, "P")) {
            return Pawn.of(Team.BLACK);
        }
        if (Objects.equals(type, "N")) {
            return Knight.of(Team.BLACK);
        }
        if (Objects.equals(type, "B")) {
            return Bishop.of(Team.BLACK);
        }
        if (Objects.equals(type, "R")) {
            return Rook.of(Team.BLACK);
        }
        if (Objects.equals(type, "Q")) {
            return Queen.of(Team.BLACK);
        }
        if (Objects.equals(type, "K")) {
            return King.of(Team.BLACK);
        }

        if (Objects.equals(type, "p")) {
            return Pawn.of(Team.WHITE);
        }
        if (Objects.equals(type, "n")) {
            return Knight.of(Team.WHITE);
        }
        if (Objects.equals(type, "b")) {
            return Bishop.of(Team.WHITE);
        }
        if (Objects.equals(type, "r")) {
            return Rook.of(Team.WHITE);
        }
        if (Objects.equals(type, "q")) {
            return Queen.of(Team.WHITE);
        }
        if (Objects.equals(type, "k")) {
            return King.of(Team.WHITE);
        }
        throw new IllegalArgumentException(UNVALID_CHESSPIECE_TYPE_EXCEPTION);
    }
}
