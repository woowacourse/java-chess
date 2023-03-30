package chess.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.piece.position.PiecePosition;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class PieceDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
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

    public void create(final PieceDto pieceDto) {
        final String query = "INSERT INTO piece(game_id, piece_type, file_position, rank_position, color) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.getGameId());
            preparedStatement.setString(2, pieceDto.getType());
            preparedStatement.setString(3, pieceDto.getFile());
            preparedStatement.setInt(4, pieceDto.getRank());
            preparedStatement.setString(5, pieceDto.getColor());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findAllIds() {
        String query = "SELECT id FROM piece";
        List<Integer> ids = new ArrayList<>();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }

    public Piece findPieceById(final PieceDto pieceDto) {
        String query = "SELECT * FROM piece WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String color = resultSet.getString("color");
                final String file = resultSet.getString("file_position");
                final int rank = resultSet.getInt("rank_position");
                final Type type = Type.ofString(resultSet.getString("piece_type"));

                final BiFunction<Color, PiecePosition, Piece> pieceConstructor = type.getPieceConstructor();
                return pieceConstructor.apply(Color.ofString(color), PiecePosition.of(file.charAt(0), rank));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(final PieceDto pieceDto) {
        final String query = "UPDATE piece SET file_position = ?, rank_position = ? WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceDto.getFile());
            preparedStatement.setInt(2, pieceDto.getRank());
            preparedStatement.setInt(3, pieceDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(final PieceDto pieceDto) {
        final String query = "DELETE FROM piece WHERE id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
