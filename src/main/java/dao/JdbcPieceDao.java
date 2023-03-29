package dao;

import dto.PieceDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPieceDao implements PieceDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(List<PieceDto> pieceDtos) {
        for (PieceDto pieceDto : pieceDtos) {
            final var query = "INSERT INTO chess_board(piece_name, piece_color, piece_row, piece_column, turn) VALUES (?, ?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pieceDto.getName());
                preparedStatement.setString(2, pieceDto.getPieceColor());
                preparedStatement.setInt(3, pieceDto.getRow());
                preparedStatement.setInt(4, pieceDto.getColumn());
                preparedStatement.setString(5, pieceDto.getColorOfTurn());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<PieceDto> find() {
        List<PieceDto> pieceDtos = new ArrayList<>();

        final var query = "SELECT piece_name, piece_color, piece_row, piece_column, turn FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pieceName = resultSet.getString("piece_name");
                String pieceColor = resultSet.getString("piece_color");
                int pieceRow = resultSet.getInt("piece_row");
                int pieceColumn = resultSet.getInt("piece_column");
                String turnOfColor = resultSet.getString("turn");

                pieceDtos.add(new PieceDto(pieceName, pieceColor, pieceRow, pieceColumn, turnOfColor));
            }
            return pieceDtos;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(List<PieceDto> pieceDtos) {
        delete();
        save(pieceDtos);
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
