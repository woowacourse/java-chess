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
        addAll(chessGameDto);
    }

    public boolean isDataExist() {
        final String sql = "SELECT count(*) AS result FROM gameInfos";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
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
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet gameInfos = statement.executeQuery(sql);
            gameInfos.next();
            return new ChessGameDto(loadPieces(), gameInfos.getString("state"), gameInfos.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<PieceDto> loadPieces() {
        final String sql = "SELECT * FROM pieces ORDER BY x ASC, y ASC";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery(sql);
            return convertToPieceDto(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<PieceDto> convertToPieceDto(ResultSet resultSet) {
        List<PieceDto> pieces = new ArrayList<>();
        try {
            while (resultSet.next()) {
                pieces.add(new PieceDto(
                        resultSet.getString("color"),
                        resultSet.getString("type"),
                        resultSet.getInt("x"),
                        resultSet.getInt("y")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    public void truncateAll() {
        final String truncatePieces = "TRUNCATE TABLE pieces";
        final String truncateGameInfos = "TRUNCATE TABLE gameInfos";

        try (final PreparedStatement piecesStatement = connection.prepareStatement(truncatePieces);
             final PreparedStatement gameInfoStatement = connection.prepareStatement(truncateGameInfos)) {

            piecesStatement.execute();
            gameInfoStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAll(ChessGameDto chessGameDto) {
        chessGameDto.getPieces()
                .forEach(this::addBoard);
        addGameInfos(chessGameDto.getState(), chessGameDto.getTurn());
    }

    private void addBoard(PieceDto pieceDto) {
        final String sql = "INSERT INTO pieces (color,type,x,y) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
        String sql = "INSERT INTO gameInfos (state,turn) VALUES ('" + state + "','" + turn + "')";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

}
