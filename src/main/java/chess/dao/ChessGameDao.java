package chess.dao;

import chess.domain.board.Board;
import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.ChessGameStatus;
import chess.domain.chessGame.EndChessGameStatus;
import chess.domain.chessGame.PlayingChessGameStatus;
import chess.domain.chessGame.ReadyChessGameStatus;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final Map<String, ChessGameStatus> chessGameStatusMapper = new HashMap<>();

    static {
        chessGameStatusMapper.put("ready", new ReadyChessGameStatus());
        chessGameStatusMapper.put("playing", new PlayingChessGameStatus());
        chessGameStatusMapper.put("end", new EndChessGameStatus());
    }

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(final ChessGame chessGame) {
        final var query = "INSERT INTO chess_game(turn, status, piece_type, piece_file, piece_rank, piece_color) VALUES(?, ?, ?, ?, ?, ?)"; // turn, status, piece_type, piece_file, piece_rank, piece_color
        Map<Position, Piece> board = chessGame.getBoard();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, chessGame.getTurnName());
                preparedStatement.setString(2, chessGame.getStatusName());
                preparedStatement.setString(3, piece.getPieceTypeName());
                preparedStatement.setString(4, position.getFileName());
                preparedStatement.setString(5, position.getRankName());
                preparedStatement.setString(6, piece.getColorName());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete() {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(final ChessGame chessGame) {
        delete();
        save(chessGame);
    }

    public ChessGame select() {
        Map<Position, Piece> board = new HashMap<>();
        Color turn = Color.WHITE;
        ChessGameStatus chessGameStatus = new ReadyChessGameStatus();

        final var query = "SELECT turn, status, piece_type, piece_file, piece_rank, piece_color FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                turn = Color.valueOf(resultSet.getString("turn"));
                chessGameStatus = chessGameStatusMapper.get(resultSet.getString("status"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                File file = File.valueOf(resultSet.getString("piece_file"));
                Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
                Color pieceColor = Color.valueOf(resultSet.getString("piece_color"));

                Position position = Position.of(file, rank);
                Piece piece = pieceType.getPiece(pieceColor);
                board.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return new ChessGame(new Board(board), chessGameStatus, turn);
    }
}
