package chess.domain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

public final class ChessGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(ChessGame chessGame) {
        String saveQuery = "INSERT INTO Board (game_id, turn, piece_file, piece_rank, piece_type, piece_team) VALUES (?, ?, ?, ?, ?, ?)";
        Map<Square, Piece> board = chessGame.getBoard();
        for (Square square : board.keySet()) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)
            ) {
                int gameId = 101;
                int turn = 1;
                char fileValue = square.getFileValue();
                char rankValue = square.getRankValue();
                Piece piece = board.get(square);
                String pieceType = PieceName.from(piece);
                int pieceTeam = piece.getColor().getValue();
                preparedStatement.setLong(1, gameId);
                preparedStatement.setInt(2, turn);
                preparedStatement.setString(3, String.valueOf(fileValue));
                preparedStatement.setString(4, String.valueOf(rankValue));
                preparedStatement.setString(5, pieceType);
                preparedStatement.setInt(6, pieceTeam);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ChessGame select() {
        final String selectQuery = "SELECT * FROM Board WHERE game_id = ? and turn = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)
        ) {
            Map<Square, Piece> board = new HashMap<>();
            preparedStatement.setInt(1, 101);
            preparedStatement.setInt(2, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                char file = resultSet.getString("piece_file").charAt(0);
                char rank = resultSet.getString("piece_rank").charAt(0);
                Square square = Square.of(File.from(file), Rank.from(rank));
                String pieceType = resultSet.getString("piece_type");
                int teamValue = resultSet.getInt("piece_team");
                try {
                    Piece piece = PieceName.toPiece(pieceType, Team.from(teamValue));
                    board.put(square, piece);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalStateException("기물을 불러오지 못했습니다.");
                }
            }
            return new ChessGame(new Board(board));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
