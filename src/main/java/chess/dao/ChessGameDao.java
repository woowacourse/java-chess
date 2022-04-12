package chess.dao;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao() {
        this.connection = SQLConnection.getConnection();
    }

    public int findChessGameIdByName(final String gameName) {
        final String sql = "select id from chess_game where name = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gameName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("해당 이름의 게임이 존재하지 않습니다.");
    }

    public void createNewChessGame(final ChessGame chessGame, final String gameName) {
        saveChessGame(gameName, chessGame.getTurn());
        final int chessGameId = findChessGameIdByName(gameName);
        savePieces(chessGame.getCurrentPlayer(), chessGameId);
        savePieces(chessGame.getOpponentPlayer(), chessGameId);
    }

    private void saveChessGame(final String gameName, final Team turn) {
        final String sql = "insert into chess_game (name, turn) values (?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, turn.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePieces(final Player player, final int chessGameId) {
        final String sql = "insert into piece (position, name, team, chess_game_id) values (?, ?, ?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final List<Piece> pieces = player.findAll();
            saveEachPiece(player, preparedStatement, pieces, chessGameId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveEachPiece(final Player player, final PreparedStatement preparedStatement, final List<Piece> pieces,
            int chessGameId)
            throws SQLException {
        for (Piece piece : pieces) {
            preparedStatement.setString(1, toPositionString(piece.getPosition()));
            preparedStatement.setString(2, String.valueOf(piece.getName()));
            preparedStatement.setString(3, player.getTeamName());
            preparedStatement.setInt(4, chessGameId);
            preparedStatement.executeUpdate();
        }
    }

    private String toPositionString(final Position position) {
        final char file = position.getFile().getValue();
        final int rank = position.getRank().getValue();
        return String.valueOf(file) + rank;
    }
}
