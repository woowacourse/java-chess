package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.boardstrategy.InjectBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import chess.dto.ChessGameDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

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

    public void create(final ChessGameDto chessGameDto) {
        final Connection connection = getConnection();
        if (checkInDB(chessGameDto)) {
            return;
        }
        final String sql = "INSERT into board (name, turn, board) values (?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessGameDto.getName());
            statement.setString(2, chessGameDto.getChessGame().getTurn().name());
            statement.setString(3, chessGameDto.getBoardJson());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkInDB(ChessGameDto chessGameDto) {
        if (findByName(chessGameDto.getName()) != null) {
            System.out.println("이미 해당 이름의 정보가 있습니다.");
            return true;
        }
        return false;
    }

    public void save(final ChessGameDto chessGameDto) {
        try {
            final Connection connection = getConnection();
            final String sql = "update board set board = ?, turn = ? where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessGameDto.getBoardJson());
            statement.setString(2, chessGameDto.getChessGame().getTurn().name());
            statement.setString(3, chessGameDto.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final String name) {
        final Connection connection = getConnection();
        final String sql = "DELETE FROM board WHERE name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameDto findByName(String name) {
        final Connection connection = getConnection();
        final String sql = "select * from board where name = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Map<Position, Piece> squares = getSquares(resultSet);
            return new ChessGameDto(resultSet.getString("name"),
                    new ChessGame(Team.valueOf(resultSet.getString("turn")), new Board(squares)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<Position, Piece> getSquares(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> squares = new HashMap<>();
        JsonArray jsonElements = new Gson().fromJson(resultSet.getString("board"), JsonArray.class);

        for (JsonElement jsonElement : jsonElements) {
            String position = jsonElement.getAsJsonObject().get("position").getAsString();
            String piece = jsonElement.getAsJsonObject().get("piece").getAsString();
            squares.put(Position.from(position), InjectBoardStrategy.getStringPieceMap().get(piece));
        }
        return squares;
    }
}
