package chess.database;

import java.sql.SQLException;

public class ChessBoardDao {
    private final JdbcConnector jdbcConnector;

    public ChessBoardDao(JdbcConnector jdbcConnector) {
        this.jdbcConnector = jdbcConnector;
    }

    public void addBoard(int gameIdx, int file, int rank, String pieceType, String side) {
        final var query = "INSERT INTO ChessBoardDB VALUES(?, ?, ?, ?, ?)";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, gameIdx);
            preparedStatement.setInt(2, file);
            preparedStatement.setInt(3, rank);
            preparedStatement.setString(4, pieceType);
            preparedStatement.setString(5, side);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findPieceType(int gameIdx, int file, int rank) {
        final var query = "select pieceType\n"
                + "from ChessBoardDB\n"
                + "where gameIdx = ? and bFile = ? and bRank = ?";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameIdx);
            preparedStatement.setInt(2, file);
            preparedStatement.setInt(3, rank);

            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("pieceType");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findPieceSide(int gameIdx, int file, int rank) {
        final var query = "select side\n"
                + "from ChessBoardDB\n"
                + "where gameIdx = ? and bFile = ? and bRank = ?";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameIdx);
            preparedStatement.setInt(2, file);
            preparedStatement.setInt(3, rank);

            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("side");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void movePiece(int gameIdx, int targetFile, int targetRank, String pieceType, String pieceSide) {
        final var query = "update ChessBoardDB\n"
                + "set pieceType = ?\n"
                + ", side = ?\n"
                + "where gameIdx = ? and bFile = ? and bRank = ?;";
        try (final var connection = jdbcConnector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceType);
            preparedStatement.setString(2, pieceSide);
            preparedStatement.setInt(3, gameIdx);
            preparedStatement.setInt(4, targetFile);
            preparedStatement.setInt(5, targetRank);
            final var resultSet = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
