package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChessGameDao {

    public void initializeChessGame(List<Piece> whitePlayerPieces, List<Piece> blackPlayerPieces) {
        resetPieces();
        savePieces(whitePlayerPieces, blackPlayerPieces);
        updateTurn("WHITE");
    }

    private void resetPieces() {
        final Connection connection = SQLConnection.getConnection();
        final String sql = "delete from piece";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePieces(List<Piece> whitePlayerPieces, List<Piece> blackPlayerPieces) {
        final Connection connection = SQLConnection.getConnection();
        final String sql = "insert into piece (position, name, team) values (?, ?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            saveWhitePiece(whitePlayerPieces, preparedStatement);
            saveBlackPiece(blackPlayerPieces, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveWhitePiece(List<Piece> whitePlayerPieces, PreparedStatement preparedStatement)
            throws SQLException {
        for (Piece piece : whitePlayerPieces) {
            final Position position = piece.getPosition();
            preparedStatement.setString(1, toPositionStringValue(position));
            preparedStatement.setString(2, String.valueOf(Character.toLowerCase(piece.getName())));
            preparedStatement.setString(3, "WHITE");
        }
        preparedStatement.executeUpdate();
    }

    private void saveBlackPiece(List<Piece> blackPlayerPieces, PreparedStatement preparedStatement)
            throws SQLException {
        for (Piece piece : blackPlayerPieces) {
            final Position position = piece.getPosition();
            preparedStatement.setString(1, toPositionStringValue(position));
            preparedStatement.setString(2, String.valueOf(piece.getName()));
            preparedStatement.setString(3, "BLACK");
        }
        preparedStatement.executeUpdate();
    }

    private String toPositionStringValue(Position position) {
        return String.valueOf(position.getFile().getValue() + position.getRank().getValue());
    }

    private void updateTurn(String turn) {
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
