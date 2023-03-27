package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.board.ChessBoard;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.game.ChessGame;
import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.piece.WhitePawn;

public class DBChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

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
        final var query = "insert into chess_board(board_file, board_rank, piece_type, team) values (?, ?, ?, ?)";

        Map<Position, Piece> board = chessGame.getChessBoard().getBoard();
        for (final Map.Entry<Position, Piece> positionPieceEntry : board.entrySet()) {

            File file = positionPieceEntry.getKey().getFile();
            Rank rank = positionPieceEntry.getKey().getRank();
            PieceType type = positionPieceEntry.getValue().getType();
            Team team = positionPieceEntry.getValue().getTeam();

            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, positionPieceEntry.getKey().getFile().name());
                preparedStatement.setString(2, Integer.toString(positionPieceEntry.getKey().getRank().getIndex()));
                preparedStatement.setString(3, positionPieceEntry.getValue().getType().name());
                preparedStatement.setString(4, positionPieceEntry.getValue().getTeam().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        Map<Position, Piece> board = new HashMap<>(64);

        final var query = "select board_file, board_rank, piece_type, team from chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = File.valueOf(resultSet.getString("board_file"));
                Rank rank = Rank.from(Integer.parseInt(resultSet.getString("board_rank")));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                Team team = Team.valueOf(resultSet.getString("team"));

                Piece piece = extractPiece(pieceType, team);
                board.put(new Position(file, rank), piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        if (board.isEmpty()) {
            return null;
        }
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        return new ChessGame(chessBoard);
    }

    private Piece extractPiece(final PieceType pieceType, final Team team) {
        switch (pieceType) {
            case KING:
                return new King(team);
            case PAWN:
                if (team == Team.BLACK) {
                    return new BlackPawn();
                }
                if (team == Team.WHITE) {
                    return new WhitePawn();
                }
            case ROOK:
                return new Rook(team);
            case EMPTY:
                return new EmptyPiece();
            case QUEEN:
                return new Queen(team);
            case BISHOP:
                return new Bishop(team);
            case KNIGHT:
                return new Knight(team);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void update(final ChessGame chessGame) {
        delete();
        save(chessGame);
    }

    private void delete() {
        final var query = "delete from chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
