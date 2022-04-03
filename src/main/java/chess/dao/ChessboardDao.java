package chess.dao;

import chess.dto.ChessGameDto;
import chess.dto.PieceDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessboardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private final Connection connection = getConnection();

    public void save(ChessGameDto chessGameDto) {
        truncateAll();
        chessGameDto.getPieces()
                .forEach(this::addBoard);
        addGameInfos(chessGameDto.getState(), chessGameDto.getTurn());
    }

    public boolean isDataExist() {
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT count(*) AS result FROM gameInfos");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("result") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ChessGameDto load() {
        final String sql = "SELECT * FROM gameInfos";
        try {
            ResultSet gameInfos = select(sql);
            gameInfos.next();
            return new ChessGameDto(loadPieces(), gameInfos.getString("state"), gameInfos.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void truncateAll() {
        final String truncatePieces = "TRUNCATE TABLE pieces";
        final String truncateGameInfos = "TRUNCATE TABLE gameInfos";
        execute(truncatePieces);
        execute(truncateGameInfos);
    }

    private List<PieceDto> loadPieces() {
        final String sql = "SELECT * FROM pieces ORDER BY x ASC, y ASC";
        List<PieceDto> pieces = new ArrayList<>();
        try {
            ResultSet resultSet = select(sql);
            while (resultSet.next()) {
                pieces.add(new PieceDto(
                        resultSet.getString("color")
                        , resultSet.getString("type")
                        , resultSet.getInt("x")
                        , resultSet.getInt("y")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    private void addBoard(PieceDto pieceDto) {
        final String sql = "INSERT INTO pieces (color,type,x,y) VALUES (?,?,?,?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pieceDto.getColor());
            statement.setString(2, pieceDto.getType());
            statement.setInt(3, pieceDto.getX());
            statement.setInt(4, pieceDto.getY());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addGameInfos(String state, String turn) {
        final String sql = "INSERT INTO gameInfos (state,turn) VALUES ('" + state + "','" + turn + "')";
        execute(sql);
    }


    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void execute(String sql) {
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet select(String sql) {
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
