package chess.database;

import chess.domain.*;
import chess.domain.chesspiece.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

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

    public void saveChessBoard(final ChessBoard chessBoard) {
        Map<Square, Piece> pieces = chessBoard.getPieces();
        for (Square square : pieces.keySet()) {
            savePiece(square, pieces.get(square));
        }
    }

    private void savePiece(final Square square, final Piece piece) {
        final var query = "INSERT INTO chess_board VALUES(?, ?, ?, ?)";
        try (final Connection connection = new ChessBoardDao().getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.getRankSymbol());
            preparedStatement.setString(2, square.getFileSymbol());
            preparedStatement.setString(3, piece.getSide());
            preparedStatement.setString(4, piece.getName());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Square, Piece> findChessBoard() {
        final var query = "SELECT * FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            Map<Square, Piece> pieces = makeEmptyChessBoard();
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Square square = Square.of(Rank.from(resultSet.getString("x"))
                            , File.from(resultSet.getString("y")));
                    Piece piece = generatePiece(resultSet.getString("type"), resultSet.getString("side"));
                    pieces.put(square, piece);
                } while (resultSet.next());
            } else {
                return null;
            }
            return pieces;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Square, Piece> makeEmptyChessBoard() {
        Map<Square, Piece> pieces = new HashMap<>();
        for (
                Rank rank : Rank.values()) {
            for (File file : File.values()) {
                pieces.put(Square.of(rank, file), EmptyPiece.getInstance());
            }
        }
        return pieces;
    }

    private Piece generatePiece(final String pieceType, final String side) {
        switch (pieceType) {
            case "KING":
                return King.from(Side.valueOf(side));
            case "QUEEN":
                return Queen.from(Side.valueOf(side));
            case "ROOK":
                return Rook.from(Side.valueOf(side));
            case "BISHOP":
                return Bishop.of(Side.valueOf(side));
            case "KNIGHT":
                return Knight.from(Side.valueOf(side));
            case "PAWN":
                return Pawn.from(Side.valueOf(side));
            case "INITIAL_PAWN":
                return InitialPawn.from(Side.valueOf(side));
            case "EMPTY_PIECE":
                return EmptyPiece.getInstance();
        }
        throw new UnsupportedOperationException();
    }

    public void updateChessBoard(final Square from, final Square to, final Piece piece) {
        final var query = "UPDATE chess_board SET" +
                " side = CASE WHEN x = ? AND y = ? THEN ? ELSE ? END," +
                " type = CASE WHEN x = ? AND y = ? THEN ? ELSE ? END" +
                " WHERE (x = ? AND y = ?) OR (X = ? AND y = ?)";
        try (final Connection connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, from.getRankSymbol());
            preparedStatement.setString(2, from.getFileSymbol());
            preparedStatement.setString(3, EmptyPiece.getInstance().getSide());
            preparedStatement.setString(4, piece.getSide());
            preparedStatement.setString(5, from.getRankSymbol());
            preparedStatement.setString(6, from.getFileSymbol());
            preparedStatement.setString(7, EmptyPiece.getInstance().getName());
            preparedStatement.setString(8, piece.getName());
            preparedStatement.setString(9, from.getRankSymbol());
            preparedStatement.setString(10, from.getFileSymbol());
            preparedStatement.setString(11, to.getRankSymbol());
            preparedStatement.setString(12, to.getFileSymbol());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteChessBoard() {
        final var query = "DELETE FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
