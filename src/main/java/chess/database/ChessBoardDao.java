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
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
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
            savePiece(chessBoard.getTurn(), square, pieces.get(square));
        }
    }

    private void savePiece(final Turn turn, final Square square, final Piece piece) {
        final var query = "INSERT INTO chess_board VALUES(?, ?, ?, ?, ?)";
        try (final Connection connection = new ChessBoardDao().getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.setString(2, square.getRank().getSymbol());
            preparedStatement.setString(3, square.getFile().getSymbol());
            preparedStatement.setString(4, piece.getSide());
            preparedStatement.setString(5, piece.getName());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessBoard findChessBoard() {
        final var query = "SELECT * FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            Map<Square, Piece> pieces = makeEmptyChessBoard();
            Turn turn;
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    turn = Turn.valueOf(resultSet.getString("turn"));
                    Square square = Square.of(Rank.from(resultSet.getString("x"))
                            , File.from(resultSet.getString("y")));
                    Piece piece = generatePiece(resultSet.getString("type"), resultSet.getString("side"));
                    pieces.put(square, piece);
                } while(resultSet.next());
            }
            else {
                return null;
            }
            return new ChessBoard(pieces, turn);
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

    public void updateChessBoard(final ChessBoard chessBoard) {
        deleteChessBoard();
        saveChessBoard(chessBoard);
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