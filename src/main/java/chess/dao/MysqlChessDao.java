package chess.dao;

import chess.dao.dto.ChessGame;
import chess.domain.piece.attribute.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.dao.DbConnectionTemplate.executeQuery;
import static chess.dao.DbConnectionTemplate.executeQueryWithGenerateKey;

public class MysqlChessDao implements ChessDao {
    @Override
    public long save(ChessGame entity) {
        String query =
                "INSERT INTO CHESSGAME " +
                        "(pieces, running, next_turn) " +
                        "VALUES (?, ?, ?)";

        return executeQueryWithGenerateKey(query, ps -> {
            ps.setString(1, entity.getPieces());
            ps.setBoolean(2, entity.isRunning());
            ps.setString(3, entity.getNextTurn().name());
        }, rs -> rs.getLong(1));
    }

    @Override
    public Optional<ChessGame> findById(long id) {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE id = ?";

        return executeQuery(query,
                (preparedStatement -> preparedStatement.setLong(1, id)),
                this::getOptionalChessGame);
    }


    @Override
    public void update(ChessGame entity) {
        String query =
                "UPDATE CHESSGAME " +
                        "SET pieces = ?, running = ? , next_turn = ?" +
                        "WHERE id = ?";

        executeQuery(query, ps -> {
            ps.setString(1, entity.getPieces());
            ps.setBoolean(2, entity.isRunning());
            ps.setString(3, entity.getNextTurn().name());
            ps.setLong(4, entity.getId());
        });
    }

    @Override
    public List<ChessGame> findAllOnRunning() {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE running = ?";

        return executeQuery(query,
                ps -> ps.setBoolean(1, true),
                this::getChessGames);
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM CHESSGAME " +
                "WHERE id = ?";

        executeQuery(query, ps -> ps.setLong(1, id));
    }

    private Optional<ChessGame> getOptionalChessGame(ResultSet resultSet) {
        try (ResultSet rs = resultSet) {
            if (!rs.next()) {
                return Optional.empty();
            }

            Long rowId = rs.getLong("id");
            boolean isRunning = rs.getBoolean("running");
            String pieces = rs.getString("pieces");
            Color nextTurn = Color.of(rs.getString("next_turn"));

            return Optional.of(new ChessGame(rowId, nextTurn, isRunning, pieces));
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<ChessGame> getChessGames(ResultSet resultSet) {
        try (ResultSet rs = resultSet) {
            List<ChessGame> chessGames = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String pieces = rs.getString("pieces");
                boolean isRunning = rs.getBoolean("running");
                Color nextTurn = Color.of(rs.getString("next_turn"));
                chessGames.add(new ChessGame(id, nextTurn, isRunning, pieces));
            }
            return chessGames;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}

