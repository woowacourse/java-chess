package dao;

import chess.ChessGame;
import chess.domain.board.Board;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DBChessGameDao implements ChessGameDao {

    /*
    CREATE TABLE chess_game (
        position VARCHAR(255) NOT NULL,
        piece_type VARCHAR(255),
        piece_color VARCHAR(255),
        turn VARCHAR(255) NOT NULL
    );
    */

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(final ChessGame chessGame) {
        final Map<Position, Piece> board = chessGame.board();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();

            final var query = "INSERT INTO chess_game (position, piece_type, piece_color, turn) VALUES (?, ?, ?, ?);";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, position.file().command() + position.rank().command());
                preparedStatement.setString(2, piece.type().name());
                preparedStatement.setString(3, piece.color().name());
                preparedStatement.setString(4, chessGame.turn().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        final Map<Position, Piece> board = new HashMap<>();
        Color turn = null;

        final var query = "SELECT * FROM chess_game;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                final String positionString = resultSet.getString("position");
                final List<String> positions = List.of(positionString.split(""));
                final Position position = Position.of(positions.get(0), positions.get(1));

                final String pieceTypeString = resultSet.getString("piece_type");
                if (pieceTypeString.isBlank()) {
                    continue;
                }
                final PieceType pieceType = PieceType.valueOf(pieceTypeString);

                final String pieceColorString = resultSet.getString("piece_color");
                if (pieceTypeString.isBlank()) {
                    continue;
                }
                final Color color = Color.valueOf(pieceColorString);

                final String turnString = resultSet.getString("turn");

                Piece piece = extractPiece(pieceType, color);
                turn = Color.valueOf(turnString);

                board.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        if (board.isEmpty()) {
            return null;
        }

        return new ChessGame(new Board(board), turn);
    }

    private Piece extractPiece(final PieceType pieceType, final Color color) {
        switch (pieceType) {
            case KING:
                return King.from(color);
            case QUEEN:
                return Queen.from(color);
            case BISHOP:
                return Bishop.from(color);
            case KNIGHT:
                return Knight.from(color);
            case ROOK:
                return Rook.from(color);
            case PAWN:
                return Pawn.from(color);
            default:
                throw new UnsupportedOperationException("안된다구요!");
        }
    }

    @Override
    public void update(final ChessGame chessGame) {
        delete();
        save(chessGame);
    }

    private void delete() {
        final var query = "DELETE FROM chess_game;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
