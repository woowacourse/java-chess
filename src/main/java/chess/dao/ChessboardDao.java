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
    private static final String EMPTY_RESULT = "실행 결과가 존재하지 않습니다.";
    private final Connection connection = getConnection();

    public boolean isDataExist() {
        final String sql = "SELECT count(*) AS result FROM gameInfos";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            validateResultSet(resultSet);
            resultSet.next();
            return resultSet.getInt("result") > 0;
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public void save(ChessGameDto chessGameDto) {
        truncateAll();
        addAll(chessGameDto);
    }

    public void truncateAll() {
        final String truncatePieces = "TRUNCATE TABLE pieces";
        final String truncateGameInfos = "TRUNCATE TABLE gameInfos";

        try (final PreparedStatement piecesStatement = connection.prepareStatement(truncatePieces);
             final PreparedStatement gameInfoStatement = connection.prepareStatement(truncateGameInfos)) {

            piecesStatement.execute();
            gameInfoStatement.execute();

        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public ChessGameDto load() {
        final String sql = "SELECT * FROM gameInfos";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet gameInfos = statement.executeQuery(sql);
            validateResultSet(gameInfos);
            gameInfos.next();
            return new ChessGameDto(loadPieces(), gameInfos.getString("state"), gameInfos.getString("turn"));
        } catch (SQLException e) {
            throw new DataAccessException();
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
            throw new DataAccessException();
        }
    }

    private void addGameInfos(String state, String turn) {
        String sql = "INSERT INTO gameInfos (state,turn) VALUES ('" + state + "','" + turn + "')";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    private List<PieceDto> loadPieces() {
        final String sql = "SELECT * FROM pieces ORDER BY x ASC, y ASC";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery(sql);
            validateResultSet(resultSet);
            return convertToPieceDto(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    private List<PieceDto> convertToPieceDto(ResultSet resultSet) {
        try {
            List<PieceDto> pieces = new ArrayList<>();
            while (resultSet.next()) {
                pieces.add(new PieceDto(
                        resultSet.getString("color"),
                        resultSet.getString("type"),
                        resultSet.getInt("x"),
                        resultSet.getInt("y")));
            }
            return pieces;
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new DataAccessException();
        }
    }

    private void validateResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException(EMPTY_RESULT);
        }
    }
}
