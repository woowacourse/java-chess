package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.status.GameStatus;
import chess.domain.status.KingAliveStatus;
import chess.domain.status.KingDeadStatus;
import chess.domain.strategy.piecemovestrategy.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGameDaoImpl implements ChessGameDao {
    private final ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public void save(final ChessGame chessGame) {
        final var pieceQuery = "INSERT INTO pieces(chess_game_id, color, type, `file`, `rank`) VALUES(?, ?, ?, ?, ?)";
        final var chessGameQuery = "INSERT INTO chess_game(id, turn, play_state) VALUES(?, ?, ?)";
        try (final var connection = connectionPool.getDatabaseConnection();
             final PreparedStatement psForChessGame = connection.prepareStatement(chessGameQuery);
             final var psForPiece = connection.prepareStatement(pieceQuery)
        ) {
            final Color turn = chessGame.getTurn();
            final ChessBoard chessBoard = chessGame.getChessBoard();
            final Map<Position, Piece> pieces = chessBoard.getPieces();

            psForChessGame.setInt(1, chessGame.getId());
            psForChessGame.setString(2, turn.name());
            if (chessGame.isGameOver()) {
                psForChessGame.setString(3, "END");
            } else {
                psForChessGame.setString(3, "PLAYING");
            }
            psForChessGame.executeUpdate();

            for (final Map.Entry<Position, Piece> positionPieceEntry : pieces.entrySet()) {
                final Piece piece = positionPieceEntry.getValue();
                final Position position = positionPieceEntry.getKey();

                psForPiece.setInt(1, chessGame.getId());
                psForPiece.setString(2, piece.getColor()
                                             .name());
                psForPiece.setString(3, piece.getPieceType()
                                             .name());
                psForPiece.setString(4, position.getFile()
                                                .name());
                psForPiece.setString(5, position.getRank()
                                                .name());

                psForPiece.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteGameById(int id) {
        String piecesQuery = "DELETE FROM pieces where chess_game_id = ?";
        String chessGameQuery = "DELETE FROM chess_game where id = ?";

        try (final var connection = connectionPool.getDatabaseConnection();
             final PreparedStatement psForPieces = connection.prepareStatement(piecesQuery);
             final PreparedStatement psForChessGame = connection.prepareStatement(chessGameQuery)
        ) {
            psForChessGame.setInt(1, id);
            psForPieces.setInt(1, id);
            psForPieces.executeUpdate();
            psForChessGame.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(final ChessGame chessGame) {
        deleteGameById(chessGame.getId());
        save(chessGame);
    }

    @Override
    public Optional<ChessGame> findById(final int id) {
        final var pieceQuery = "SELECT color, type, `file`, `rank` from pieces WHERE chess_game_id = ?";
        final var chessGameQuery = "SELECT id, turn, play_state from chess_game where id = ?";
        try (final var connection = connectionPool.getDatabaseConnection();
             final PreparedStatement psForChessGame = connection.prepareStatement(chessGameQuery);
             final var psForPiece = connection.prepareStatement(pieceQuery)
        ) {
            psForChessGame.setInt(1, id);
            psForPiece.setInt(1, id);

            final ResultSet resultSetForChessGame = psForChessGame.executeQuery();
            int gameId = 0;
            Color turn = null;
            GameStatus gameState = null;

            if (resultSetForChessGame.next()) {
                gameId = resultSetForChessGame.getInt("id");
                turn = Color.valueOf(resultSetForChessGame.getString("turn"));
                final String playState = resultSetForChessGame.getString("play_state");
                if (playState.equals("ENDING")) {
                    gameState = new KingDeadStatus(turn);
                } else {
                    gameState = new KingAliveStatus(turn);
                }
            }

            Map<Position, Piece> map = new HashMap<>();
            final ResultSet resultSetForPieces = psForPiece.executeQuery();
            while (resultSetForPieces.next()) {
                final Color color = Color.valueOf(resultSetForPieces.getString("color"));
                final PieceType type = PieceType.valueOf(resultSetForPieces.getString("type"));
                final File file = File.valueOf(resultSetForPieces.getString("file"));
                final Rank rank = Rank.valueOf(resultSetForPieces.getString("rank"));
                final Position position = Position.of(rank, file);
                if (type == PieceType.PAWN) {
                    map.put(position, new Pawn(color, position, PawnMoveStrategy.from(color)));
                } else if (type == PieceType.KING) {
                    map.put(position, new NonPawnPiece(color, position, KingMove.getInstance()));
                } else if (type == PieceType.QUEEN) {
                    map.put(position, new NonPawnPiece(color, position, QueenMove.getInstance()));
                } else if (type == PieceType.ROOK) {
                    map.put(position, new NonPawnPiece(color, position, RookMove.getInstance()));
                } else if (type == PieceType.BISHOP) {
                    map.put(position, new NonPawnPiece(color, position, BishopMove.getInstance()));
                } else if (type == PieceType.KNIGHT) {
                    map.put(position, new NonPawnPiece(color, position, KnightMove.getInstance()));
                } else {
                    map.put(position, new Empty(position));
                }
            }
            if (turn == null) {
                return Optional.empty();
            }
            return Optional.of(ChessGame.createChessGame(gameId, ChessBoard.of(map), gameState));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
