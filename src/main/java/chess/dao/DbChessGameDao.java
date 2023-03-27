package chess.dao;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.game.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.score.ScoreCalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DbChessGameDao {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "12345678"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(final ChessGame chessGame) {
        Map<Position, Piece> piecePosition = chessGame.getChessBoardMap();

        for (Map.Entry<Position, Piece> positionPieceEntry : piecePosition.entrySet()) {
            final var query = "INSERT INTO chess_game(piece_type, piece_color, piece_column, piece_rank) VALUES (?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, positionPieceEntry.getValue().findName());
                preparedStatement.setString(2, positionPieceEntry.getValue().getColor().toString());
                preparedStatement.setString(3, positionPieceEntry.getKey().getColumn().name());
                preparedStatement.setString(4, positionPieceEntry.getKey().getRank().name());
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ChessGame select() {
        Map<Position, Piece> pieces = new LinkedHashMap<>();

        final var query = "SELECT piece_type, piece_color, piece_column, piece_rank FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Piece piece = Piece.valueOf(resultSet.getString("piece_type"));
                Column pieceColumn = Column.valueOf(resultSet.getString("piece_column"));
                Rank pieceRank = Rank.valueOf(resultSet.getString("piece_rank"));

                Position position = new Position(pieceColumn, pieceRank);
                pieces.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        if (pieces.isEmpty()) {
            return null;
        }

        ChessBoard chessBoard = new ChessBoard(pieces);

        return new ChessGame(chessBoard);
    }

    private Piece extractPiece(final Color pieceColor, final Piece pieceType) {
        return pieceType.getInstance(pieceColor);
    }

    public void update(final ChessGame chessGame) {
        delete(chessGame);
        save(chessGame);
    }

    private void delete(final ChessGame chessGame) {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
