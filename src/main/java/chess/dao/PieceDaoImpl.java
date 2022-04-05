package chess.dao;

import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.MoveDto;
import chess.dto.PieceDto;
import chess.utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PieceDaoImpl implements PieceDao {

    private final Connection connection;

    public PieceDaoImpl() {
        connection = DbConnector.getConnection();
    }

    @Override
    public void initializePieces(final Player player) {
        final String sql = "insert into piece (position, team, name) values (?, ?, ?)";
        List<PieceDto> pieces = player.findAll()
                .stream()
                .map(PieceDto::from)
                .collect(Collectors.toUnmodifiableList());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            initializePiece(player, pieces, statement);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializePiece(final Player player, final List<PieceDto> pieces,
                                 final PreparedStatement statement) throws SQLException {
        for (PieceDto piece : pieces) {
            statement.setString(1, piece.getPosition());
            statement.setString(2, player.getTeamName());
            statement.setString(3, piece.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public List<PieceDto> findPiecesByTeam(final Team team) {
        final String sql = "select * from piece where piece.team = ?";
        final List<PieceDto> pieces = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getName());
            final ResultSet resultSet = statement.executeQuery();
            addPiece(resultSet, pieces);
            return pieces;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    private void addPiece(final ResultSet resultSet, final List<PieceDto> pieces) throws SQLException {
        while (resultSet.next()) {
            final String position = resultSet.getString("position");
            final String pieceName = resultSet.getString("name");
            pieces.add(new PieceDto(position, pieceName));
        }
    }

    @Override
    public void updatePiece(final MoveDto moveDto) {
        final String sql = "update piece set position = ? where position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, moveDto.getDestinationPosition());
            statement.setString(2, moveDto.getCurrentPosition());
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePieceByCaptured(final MoveDto moveDto) {
        final String sql = "delete from piece where position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, moveDto.getDestinationPosition());
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endPieces() {
        final String sql = "truncate table piece";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
