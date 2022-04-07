package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao() {
        this.connection = SQLConnection.getConnection();
    }

    public List<PieceDto> findAllPiece() {
        final String sql = "select * from piece";
        final List<PieceDto> pieces = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String position = resultSet.getString("position");
                final String name = resultSet.getString("name");
                pieces.add(new PieceDto(position, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    public void initializeChessGame(final Player whitePlayer, final Player blackPlayer) {
        resetPieces();
        savePieces(whitePlayer);
        savePieces(blackPlayer);
        updateTurn("WHITE");
    }

    public void resetPieces() {
        final String sql = "delete from piece";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePieces(final Player player) {
        final String sql = "insert into piece (position, name, team) values (?, ?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final List<Piece> pieces = player.findAll();
            savePiece(player, preparedStatement, pieces);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePiece(final Player player, final PreparedStatement preparedStatement, final List<Piece> pieces)
            throws SQLException {
        for (Piece piece : pieces) {
            preparedStatement.setString(1, toPositionString(piece.getPosition()));
            preparedStatement.setString(2, String.valueOf(piece.getName()));
            preparedStatement.setString(3, player.getTeamName());
            preparedStatement.executeUpdate();
        }
    }

    private String toPositionString(final Position position) {
        final char file = position.getFile().getValue();
        final int rank = position.getRank().getValue();
        return String.valueOf(file) + rank;
    }

    private void updateTurn(final String turn) {
        final String sql = "insert into chess_game (turn) values (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, turn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
