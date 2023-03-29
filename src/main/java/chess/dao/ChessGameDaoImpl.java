package chess.dao;

import chess.chessboard.*;
import chess.chessgame.ChessGame;
import chess.piece.Piece;
import chess.piece.PieceFactory;
import chess.piece.PieceType;

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
        final var pieceQuery = "INSERT INTO pieces(chess_game_id, side, type, `file`, `rank`) VALUES(?, ?, ?, ?, ?)";
        final var chessGameQuery = "INSERT INTO chess_game(id, turn) VALUES(?, ?)";
        try (final var connection = connectionPool.getDatabaseConnection();
             final PreparedStatement psForChessGame = connection.prepareStatement(chessGameQuery);
             final var psForPiece = connection.prepareStatement(pieceQuery)
        ) {
            final Side turn = chessGame.getTurn();
            final ChessBoard chessBoard = chessGame.getChessBoard();
            final Map<Position, Piece> pieces = chessBoard.getPieces();

            psForChessGame.setInt(1, 1);
            psForChessGame.setString(2, turn.name());
            psForChessGame.executeUpdate();

            for (final Map.Entry<Position, Piece> positionPieceEntry : pieces.entrySet()) {
                final Piece piece = positionPieceEntry.getValue();
                final Position position = positionPieceEntry.getKey();

                psForPiece.setInt(1, 1);
                psForPiece.setString(2, piece.getSide()
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
    public void delete() {
        String piecesQuery = "DELETE FROM pieces";
        String chessGameQuery = "DELETE FROM chess_game";

        try (final var connection = connectionPool.getDatabaseConnection();
             final PreparedStatement psForPieces = connection.prepareStatement(piecesQuery);
             final PreparedStatement psForChessGame = connection.prepareStatement(chessGameQuery)
        ) {
            psForPieces.executeUpdate();
            psForChessGame.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(final ChessGame chessGame) {
        delete();
        save(chessGame);
    }

    @Override
    public Optional<ChessGame> find() {
        final var pieceQuery = "SELECT side, type, `file`, `rank` from pieces";
        final var chessGameQuery = "SELECT turn from chess_game";
        try (final var connection = connectionPool.getDatabaseConnection();
             final PreparedStatement psForChessGame = connection.prepareStatement(chessGameQuery);
             final var psForPiece = connection.prepareStatement(pieceQuery)
        ) {
            final ResultSet resultSetForChessGame = psForChessGame.executeQuery();
            Side turn = null;

            if (resultSetForChessGame.next()) {
                turn = Side.valueOf(resultSetForChessGame.getString("turn"));
            }
            Map<Position, Piece> map = new HashMap<>();
            final ResultSet resultSetForPieces = psForPiece.executeQuery();
            while (resultSetForPieces.next()) {
                final Side side = Side.valueOf(resultSetForPieces.getString("side"));
                final PieceType type = PieceType.valueOf(resultSetForPieces.getString("type"));
                final File file = File.valueOf(resultSetForPieces.getString("file"));
                final Rank rank = Rank.valueOf(resultSetForPieces.getString("rank"));
                final Position position = Position.of(rank, file);
                map.put(position, PieceFactory.generate(type, side));
            }
            if (turn == null) {
                return Optional.empty();
            }
            return Optional.of(new ChessGame(turn, new ChessBoard(map)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
