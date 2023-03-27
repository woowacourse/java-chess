package chess.domain.entity;

import chess.domain.board.Position;
import chess.domain.pieces.Piece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PieceDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

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

    public void addPiece(final PieceEntity boardTable) {
        final var query = "INSERT INTO piece(piece_type, piece_file, piece_rank, piece_gameId) VALUES(?, ?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            System.out.println("boardTable = " + boardTable);
            preparedStatement.setString(1, boardTable.getType());
            preparedStatement.setInt(2, boardTable.getFile());
            preparedStatement.setInt(3, boardTable.getRank());
            preparedStatement.setInt(4, boardTable.getGameId());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Piece findByPosition(final Position position) {
        int file = position.getFile();
        int rank = position.getRank();

        final var query = "SELECT * FROM piece WHERE piece_file = ? AND piece_rank = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, file);
            preparedStatement.setInt(2, rank);

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return PieceNameConverter.convert(resultSet.getString("piece_type"));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void updateByPiecePosition(final Position position, final Piece piece) {
        String pieceType = piece.getType().getName();
        int file = position.getFile();
        int rank = position.getRank();

        final var query = "UPDATE piece SET piece_type = ? WHERE piece_file = ? AND piece_rank = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceType);
            preparedStatement.setInt(2, file);
            preparedStatement.setInt(3, rank);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasData() {
        final var query = "SELECT COUNT(*) AS COUNT FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT") != 0;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
