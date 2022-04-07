package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChessGameDao {

    public void initializeChessGame(final Player whitePlayer, final Player blackPlayer) {
        resetPieces();
        savePieces(whitePlayer);
        savePieces(blackPlayer);
        updateTurn("WHITE");
    }

    public void resetPieces() {
        final Connection connection = SQLConnection.getConnection();
        final String sql = "delete from piece";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePieces(final Player player) {
        final Connection connection = SQLConnection.getConnection();
        final String sql = "insert into piece (position, name, team) values (?, ?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final List<Piece> pieces = player.findAll();
            savePiece(player, preparedStatement, pieces);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePiece(Player player, PreparedStatement preparedStatement, List<Piece> pieces) throws SQLException {
        for (Piece piece : pieces) {
            preparedStatement.setString(1, toPositionString(piece.getPosition()));
            preparedStatement.setString(2, String.valueOf(piece.getName()));
            preparedStatement.setString(3, player.getTeamName());
        }
        preparedStatement.executeUpdate();
    }

    private String toPositionString(final Position position) {
        final char file = position.getFile().getValue();
        final int rank = position.getRank().getValue();
        return String.valueOf(file + rank);
    }

    private void updateTurn(final String turn) {
        final Connection connection = SQLConnection.getConnection();
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
