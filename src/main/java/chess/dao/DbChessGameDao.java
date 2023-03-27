package chess.dao;

import chess.domain.board.ChessBoard;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.game.ChessGame;
import chess.domain.game.GameStatus;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbChessGameDao implements ChessGameDao {

    private static final int INITIAL_BOARD_CAPACITY = 64;

    @Override
    public void save(final ChessGame chessGame) {
        Map<Position, Piece> piecePosition = chessGame.getChessBoard().getPiecePosition();
        for (final Map.Entry<Position, Piece> positionPieceEntry : piecePosition.entrySet()) {
            final var query = "INSERT INTO chess_game(piece_type, piece_file, piece_rank, piece_team, game_status, turn) VALUES (?, ?, ?, ?, ?, ?)";
            try (final var connection = DbConnection.getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, positionPieceEntry.getValue().getType().name());
                preparedStatement.setString(2, positionPieceEntry.getKey().getFile().name());
                preparedStatement.setString(3, positionPieceEntry.getKey().getRank().name());
                preparedStatement.setString(4, positionPieceEntry.getValue().getTeam().name());
                preparedStatement.setString(5, chessGame.getStatus().name());
                preparedStatement.setString(6, chessGame.getCurrentTeam().name());
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        Map<Position, Piece> pieces = new HashMap<>(INITIAL_BOARD_CAPACITY);
        Team turn = null;
        GameStatus gameStatus = GameStatus.IDLE;

        final var query = "SELECT piece_type, piece_file, piece_rank, piece_team, turn, game_status FROM chess_game";
        try (final var connection = DbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File pieceFile = File.valueOf(resultSet.getString("piece_file"));
                Rank pieceRank = Rank.valueOf(resultSet.getString("piece_rank"));
                Team pieceTeam = Team.valueOf(resultSet.getString("piece_team"));
                turn = Team.valueOf(resultSet.getString("turn"));
                gameStatus = GameStatus.valueOf(resultSet.getString("game_status"));
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

        return new ChessGame(chessBoard, new Turn(turn), gameStatus);
    }

    private Piece extractPiece(final Team pieceTeam, final PieceType pieceType) {
        return pieceType.getInstance(pieceTeam);
    }

    @Override
    public void update(final ChessGame chessGame) {
        delete(chessGame);
        save(chessGame);
    }

    private void delete(final ChessGame chessGame) {
        final var query = "DELETE FROM chess_game";
        try (final var connection = DbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
