package chess.dao;

import chess.domain.game.dto.LoadedPiecesInsertDto;
import chess.domain.game.dto.LoadedPiecesSelectDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPiecesDao implements PiecesDao {
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

    @Override
    public LoadedPiecesSelectDto findAll() {
        final var query = "SELECT position_file, position_rank, piece_side, piece_type FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            List<Integer> files = new ArrayList<>();
            List<Integer> ranks = new ArrayList<>();
            List<String> sides = new ArrayList<>();
            List<String> types = new ArrayList<>();

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                files.add(resultSet.getInt("position_file"));
                ranks.add(resultSet.getInt("position_rank"));
                sides.add(resultSet.getString("piece_side"));
                types.add(resultSet.getString("piece_type"));
            }
            return new LoadedPiecesSelectDto(files, ranks, sides, types);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertAll(LoadedPiecesInsertDto piecesInsertDto) {
        final List<Integer> files = piecesInsertDto.getFiles();
        final List<Integer> ranks = piecesInsertDto.getRanks();
        final List<String> sides = piecesInsertDto.getSides();
        final List<String> pieceTypes = piecesInsertDto.getPieceTypes();

        for (int pieceIndex = 0; pieceIndex < pieceTypes.size(); pieceIndex++) {
            final int file = files.get(pieceIndex);
            final int rank = ranks.get(pieceIndex);
            final String side = sides.get(pieceIndex);
            final String pieceType = pieceTypes.get(pieceIndex);

            insert(file, rank, side, pieceType);
        }
    }

    private void insert(final int file, final int rank, final String side, final String pieceType) {
        final var query = "INSERT INTO piece(position_file, position_rank, piece_side, piece_type) VALUES (?, ?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, file);
            preparedStatement.setInt(2, rank);
            preparedStatement.setString(3, side);
            preparedStatement.setString(4, pieceType);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
