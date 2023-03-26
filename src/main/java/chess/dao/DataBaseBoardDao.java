package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;

public class DataBaseBoardDao implements BoardDao {

    @Override
    public void saveBoard(final Board board, final long gameId) {
        final String saveBoardQuery = "INSERT INTO board VALUES (?,?,?,?,?)";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(saveBoardQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, gameId);
            for (final Entry<Position, Piece> positionPieceEntry : board.getBoard().entrySet()) {
                final Position position = positionPieceEntry.getKey();
                final Piece piece = positionPieceEntry.getValue();
                preparedStatement.setInt(2, position.file().value());
                preparedStatement.setInt(3, position.rank().value());
                preparedStatement.setString(4, piece.getColor().name());
                preparedStatement.setString(5, piece.getPieceType().name());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePiecePosition(final Position from, final Position to) {

    }

    @Override
    public Board loadBoard(final Long gameId) {
        return null;
    }

    @Override
    public void deleteGame() {

    }
}
