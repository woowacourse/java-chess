package chess.dao;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.PieceInfoDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JdbcPieceDao implements PieceDao {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "username"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PieceInfoDto> findById(int gameId) {
        List<PieceInfoDto> pieces = new ArrayList<>();

        final var pieceQuery = "SELECT * FROM piece WHERE game_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = File.valueOf(resultSet.getString("piece_file"));
                Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
                Color color = Color.valueOf(resultSet.getString("piece_team"));
                PieceType type = PieceType.valueOf(resultSet.getString("piece_type"));
                pieces.add(PieceInfoDto.create(file, rank, color, type));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return pieces;
    }

    @Override
    public void save(int gameId, ChessGame chessGame) {
        Map<Position, Piece> positionAndMap = chessGame.getBoard().getPositionAndPiece();
        for (final Map.Entry<Position, Piece> positionPieceEntry : positionAndMap.entrySet()) {
            final var pieceQuery = "INSERT INTO piece VALUES(?, ?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(pieceQuery)) {
                preparedStatement.setInt(1, gameId);
                preparedStatement.setString(2, positionPieceEntry.getKey().getFile().name());
                preparedStatement.setString(3, positionPieceEntry.getKey().getRank().name());
                preparedStatement.setString(4, positionPieceEntry.getValue().getType().name());
                preparedStatement.setString(5, positionPieceEntry.getValue().getColor().name());
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void updateById(int gameId, ChessGame chessGame) {
        deleteById(gameId);
        save(gameId, chessGame);
    }

    @Override
    public void deleteById(int gameId) {
        final var pieceQuery = "DELETE FROM piece WHERE game_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        final var pieceQuery = "DELETE FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
