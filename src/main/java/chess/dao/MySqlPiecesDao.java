package chess.dao;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Side;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
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

    @Override
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
    public List<Piece> findAll() {
        final var query = "SELECT position_file, position_rank, piece_side, piece_type FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            List<Piece> pieces = new ArrayList<>();

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int file = resultSet.getInt("position_file");
                final int rank = resultSet.getInt("position_rank");
                final String sideName = resultSet.getString("piece_side");
                final String pieceType = resultSet.getString("piece_type");

                final Position position = new Position(File.of(file), Rank.of(rank));
                pieces.add(extractPiece(sideName, pieceType, position));
            }
            return pieces;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece extractPiece(final String sideName, final String pieceType, final Position position) {
        switch (pieceType) {
            case "King":
                return new King(position, Side.valueOf(sideName));
            case "Queen":
                return new Queen(position, Side.valueOf(sideName));
            case "Bishop":
                return new Bishop(position, Side.valueOf(sideName));
            case "Knight":
                return new Knight(position, Side.valueOf(sideName));
            case "Rook":
                return new Rook(position, Side.valueOf(sideName));
            case "Pawn":
                return new Pawn(position, Side.valueOf(sideName));
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void insertAll(List<Piece> pieces) {
        final var query = "INSERT INTO piece(position_file, position_rank, piece_side, piece_type) VALUES (?, ?, ?, ?)";
        for (Piece piece : pieces) {
            final int file = piece.getFile();
            final int rank = piece.getRank();
            final Side side = piece.getSide();
            final String type = piece.getClass().getSimpleName();

            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, file);
                preparedStatement.setInt(2, rank);
                preparedStatement.setString(3, side.name());
                preparedStatement.setString(4, type);
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
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
