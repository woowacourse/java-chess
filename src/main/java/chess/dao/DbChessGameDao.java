package chess.dao;

import chess.domain.board.ChessBoard;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.game.ChessGame;
import chess.domain.game.GameStatus;
import chess.domain.game.Turn;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*
CREATE TABLE chess_game {
    piece_type VARCHAR(255) NOT NULL,
    piece_file VARCHAR(255) NOT NULL,
    piece_rank VARCHAR(255) NOT NULL,
    piece_team VARCHAR(255) NOT NULL,
    turn VARCHAR(255) NOT NULL,
}

DELETE FROM chess_game;
 */
public class DbChessGameDao implements ChessGameDao {

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
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(final ChessGame chessGame) {
        Map<Position, Piece> piecePosition = chessGame.getChessBoard().getPiecePosition();
        for (final Map.Entry<Position, Piece> positionPieceEntry : piecePosition.entrySet()) {
            final var query = "INSERT INTO chess_game(piece_type, piece_file, piece_rank, piece_team, turn) VALUES (?, ?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, positionPieceEntry.getValue().getType().name());
                preparedStatement.setString(2, positionPieceEntry.getKey().getFile().name());
                preparedStatement.setString(3, positionPieceEntry.getKey().getRank().name());
                preparedStatement.setString(4, positionPieceEntry.getValue().getTeam().name());
                preparedStatement.setString(5, chessGame.getCurrentTeam().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        Map<Position, Piece> pieces = new HashMap<>(64);
        Team turn = null;

        final var query = "SELECT piece_type, piece_file, piece_rank, piece_team, turn FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File pieceFile = File.valueOf(resultSet.getString("piece_file"));
                Rank pieceRank = Rank.valueOf(resultSet.getString("piece_rank"));
                Team pieceTeam = Team.valueOf(resultSet.getString("piece_team"));
                turn = Team.valueOf(resultSet.getString("turn"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));

                Position position = Position.of(pieceFile, pieceRank);
                Piece piece = extractPiece(pieceTeam, pieceType);
                pieces.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        if (pieces.isEmpty()) {
            return null;
        }

        ChessBoard chessBoard = ChessBoard.createBoardByRule(pieces);

        return new ChessGame(chessBoard, new Turn(turn), GameStatus.START);
    }

    private Piece extractPiece(final Team pieceTeam, final PieceType pieceType) {
        switch (pieceType) {
            case KING:
                return new King(pieceTeam);
            case PAWN:
                return new Pawn(pieceTeam);
            case ROOK:
                return new Rook(pieceTeam);
            case EMPTY:
                return new EmptyPiece();
            case QUEEN:
                return new Queen(pieceTeam);
            case BISHOP:
                return new Bishop(pieceTeam);
            case KNIGHT:
                return new Knight(pieceTeam);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
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
