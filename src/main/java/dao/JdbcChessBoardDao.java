package dao;

import domain.chessGame.ChessBoard;
import domain.chessGame.ChessBoardGenerator;
import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.PieceName;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.position.Position;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JdbcChessBoardDao implements ChessBoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
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

    @Override
    public void save(ChessBoard chessBoard) {
        // Todo : 현재 체스 보드 정보를 저장하기
        Map<Position, Piece> board = chessBoard.getChessBoard();

        for (Entry<Position, Piece> entry : board.entrySet()) {
            final var query = "INSERT INTO chess_board(piece_name, piece_color, piece_row, piece_column, turn) VALUES (?, ?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, entry.getValue().getName());
                preparedStatement.setString(2, entry.getValue().getColor().name());
                preparedStatement.setInt(3, entry.getKey().getRow());
                preparedStatement.setInt(4, entry.getKey().getColumn());
                preparedStatement.setString(5, chessBoard.getTurnOfColor().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessBoard select() {
        // Todo: 체스 보드 생성해주기
        Map<Position, Piece> loadBoard = new HashMap<>();
        Color turnOfColor = null;

        final var query = "SELECT piece_name, piece_color, piece_row, piece_column, turn FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PieceName pieceName = PieceName.of(resultSet.getString("piece_name"));
                Color pieceColor = Color.valueOf(resultSet.getString("piece_color"));
                Position position = Position.of(resultSet.getInt("piece_row"), resultSet.getInt("piece_column"));
                turnOfColor = Color.valueOf(resultSet.getString("turn"));

                Piece piece = makePiece(pieceName, pieceColor);

                loadBoard.put(position, piece);
            }

            if (turnOfColor == null) {
                ChessBoardGenerator generator = new ChessBoardGenerator();
                return new ChessBoard(generator.generate());
            }

            return new ChessBoard(loadBoard, turnOfColor);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece makePiece(PieceName pieceName, Color pieceColor) {
        switch (pieceName) {
            case KING:
                return new King(pieceColor);
            case PAWN:
                if (pieceColor == Color.BLACK) {
                    return new BlackPawn();
                }
                return new WhitePawn();
            case ROOK:
                return new Rook(pieceColor);
            case QUEEN:
                return new Queen(pieceColor);
            case BISHOP:
                return new Bishop(pieceColor);
            case KNIGHT:
                return new Knight(pieceColor);
            default:
                throw new UnsupportedOperationException("[ERROR] DB를 불러오는 과정에서 오류가 발생했습니다.");
        }
    }

    @Override
    public void update(ChessBoard chessBoard) {
        delete();
        save(chessBoard);
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
