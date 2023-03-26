package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.game.Camp;
import chess.domain.game.Game;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user"; //  MySQL 서버 아이디
    private static final String PASSWORD = "password"; // MySQL 서버 비밀번호

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

    public void addMove(final Game game) {
        final var query = "INSERT INTO game (turn,"
                + "a8, b8, c8, d8, e8, f8, g8, h8,"
                + "a7, b7, c7, d7, e7, f7, g7, h7,"
                + "a6, b6, c6, d6, e6, f6, g6, h6,"
                + "a5, b5, c5, d5, e5, f5, g5, h5,"
                + "a4, b4, c4, d4, e4, f4, g4, h4,"
                + "a3, b3, c3, d3, e3, f3, g3, h3,"
                + "a2, b2, c2, d2, e2, f2, g2, h2,"
                + "a1, b1, c1, d1, e1, f1, g1, h1)"
                + "VALUES(?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ?, ?, ?)";

        final List<Piece> pieces = game.getPieces();

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, game.turn().getName());
            for (int i = 0; i < pieces.size(); i++) {
                preparedStatement.setString(i + 2, getPiece(pieces.get(i)));
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPiece(final Piece piece) {
        if (piece.isSameCamp(Camp.WHITE)) {
            return piece.pieceType().whiteInitial();
        }
        return piece.pieceType().blackInitial();
    }

    public Game restoreLastGame() {
        final var query = "SELECT * FROM game ORDER BY id DESC LIMIT 1;";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final List<Piece> pieces = extractPieces(resultSet);
                final Board board = new Board(BoardFactory.createBoard(pieces));
                final Camp turn = Camp.nameToCamp(resultSet.getString("turn"));
                return new Game(board, turn);
            }
            return new Game();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Piece> extractPieces(final ResultSet resultSet) throws SQLException {
        final List<Piece> pieces = new ArrayList<>();
        final List<String> positions = List.of(
                "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"
        );

        for (final String position : positions) {
            pieces.add(resultToPiece(resultSet.getString(position), position));
        }

        return pieces;
    }

    private Piece resultToPiece(final String initial, final String label) {
        final PieceType pieceType = PieceType.initialToPieceType(initial);
        final Camp camp = PieceType.initialToCamp(initial);
        final Square position = new Square(label);

        if (pieceType == PieceType.PAWN) {
            return new Pawn(camp, position);
        }
        if (pieceType == PieceType.ROOK) {
            return new Rook(camp, position);
        }
        if (pieceType == PieceType.BISHOP) {
            return new Bishop(camp, position);
        }
        if (pieceType == PieceType.KNIGHT) {
            return new Knight(camp, position);
        }
        if (pieceType == PieceType.QUEEN) {
            return new Queen(camp, position);
        }
        if (pieceType == PieceType.KING) {
            return new King(camp, position);
        }
        return new Empty(position);
    }
}
