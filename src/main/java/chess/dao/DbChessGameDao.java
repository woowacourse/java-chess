package chess.dao;

import chess.domain.*;
import chess.domain.piece.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbChessGameDao implements ChessGameDao {

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


    @Override
    public void save(ChessGame chessGame) {
        Map<Position, ChessPiece> chessBoard = chessGame.getChessBoard();
        for (Map.Entry<Position, ChessPiece> chessPieceEntry : chessBoard.entrySet()) {
            final var query = "INSERT INTO chess_game(piece_type, piece_column, piece_rank, color, turn) VALUES(?, ?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, chessPieceEntry.getValue().getShape().name());
                preparedStatement.setString(2, chessPieceEntry.getKey().getColumn().name());
                preparedStatement.setString(3, chessPieceEntry.getKey().getRank().name());
                preparedStatement.setString(4, chessPieceEntry.getValue().getColor().name());
                preparedStatement.setString(5, chessGame.getColor().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        Map<Position, ChessPiece> pieces = new HashMap<>(64);
        Color gameTurn = null;
        final var query = "SELECT * FROM chess_game";
        gameTurn = createChessBoard(pieces, gameTurn, query);

        if (pieces.isEmpty()) {
            return null;
        }
        ChessBoard chessBoard = ChessBoard.makeOnGameChessBoard(pieces);
        ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.setColor(gameTurn);
        chessGame.startGame();

        return chessGame;
    }

    private Color createChessBoard(Map<Position, ChessPiece> pieces, Color gameTurn, String query) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Column column = Column.valueOf(resultSet.getString("piece_column"));
                Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
                Color color = Color.valueOf(resultSet.getString("color"));
                gameTurn = Color.valueOf(resultSet.getString("turn"));

                Shape pieceType = Shape.valueOf(resultSet.getString("piece_type"));

                ChessPiece chessPiece = extractPiece(pieceType, color);
                Position piecePosition = new Position(column, rank);
                pieces.put(piecePosition, chessPiece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return gameTurn;
    }

    private ChessPiece extractPiece(Shape pieceType, Color color) {
        switch (pieceType) {
            case WHITE_PAWN:
                return new Pawn(color);
            case BLACK_PAWN:
                return new Pawn(color);
            case WHITE_ROOK:
                return new Rook(color);
            case BLACK_ROOK:
                return new Rook(color);
            case WHITE_KNIGHT:
                return new Knight(color);
            case BLACK_KNIGHT:
                return new Knight(color);
            case WHITE_BISHOP:
                return new Bishop(color);
            case BLACK_BISHOP:
                return new Bishop(color);
            case WHITE_QUEEN:
                return new Queen(color);
            case BLACK_QUEEN:
                return new Queen(color);
            case WHITE_KING:
                return new King(color);
            case BLACK_KING:
                return new King(color);
            case BLANK:
                return new Empty();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void update(ChessGame chessGame) {
        delete();
        save(chessGame);
    }

    private void delete() {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}