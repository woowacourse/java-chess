package chess.repository;

import chess.entity.ChessGame;
import chess.piece.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.repository.ChessConnection.getConnection;

public class MariaChessRepository implements ChessRepository {

    @Override
    public ChessGame save(ChessGame entity) throws SQLException {
        String query =
                "INSERT INTO CHESSGAME " +
                        "(active, winner, createdTime) " +
                        "VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setBoolean(1, entity.isActive());
        preparedStatement.setString(2, entity.getWinner().name());
        LocalDateTime createdTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(createdTime));

        preparedStatement.executeQuery();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        if (!generatedKeys.next()) {
            return entity;
        }

        return new ChessGame(generatedKeys.getLong("id"), createdTime, entity);
    }

    @Override
    public Optional<ChessGame> findById(Long id) throws SQLException {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }
        ChessGame chessGame = collectEntity(resultSet);

        return Optional.of(chessGame);
    }

    private ChessGame collectEntity(ResultSet resultSet) throws SQLException {
        Long rowId = resultSet.getLong("id");
        boolean active = resultSet.getBoolean("active");
        Team winningTeam = Team.valueOf(resultSet.getString("winner"));
        LocalDateTime createdTime = resultSet.getTimestamp("createdTime").toLocalDateTime();
        return new ChessGame(rowId, winningTeam, active, createdTime);
    }

    @Override
    public void update(ChessGame entity) throws SQLException {
        String query =
                "UPDATE CHESSGAME " +
                        "SET active = ? , winner = ?" +
                        "WHERE id = ?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1, entity.isActive());
        preparedStatement.setString(2, entity.getWinner().name());
        preparedStatement.setLong(3, entity.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public List<ChessGame> findAll() throws SQLException {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<ChessGame> chessGames = new ArrayList<>();
        while (resultSet.next()) {
            chessGames.add(collectEntity(resultSet));
        }
        return chessGames;
    }

    @Override
    public List<ChessGame> findAllByActive() throws SQLException {
        String query =
                "SELECT * " +
                        "FROM CHESSGAME " +
                        "WHERE active = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1, true);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<ChessGame> chessGames = new ArrayList<>();
        while (resultSet.next()) {
            ChessGame chessGame = collectEntity(resultSet);
            chessGames.add(chessGame);
        }
        return chessGames;
    }

    @Override
    public void deleteAll() throws SQLException {
        String query = "DELETE FROM CHESSGAME";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.executeUpdate();
    }
}
