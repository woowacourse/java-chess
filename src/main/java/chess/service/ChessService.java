package chess.service;

import chess.ChessGame;
import chess.dao.ChessStatusDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class ChessService {

    private final PieceDao pieceDao;
    private final ChessStatusDao chessStatusDao;
    private String gameId;

    public ChessService(final PieceDao pieceDao, final ChessStatusDao chessStatusDao) {
        this.pieceDao = pieceDao;
        this.chessStatusDao = chessStatusDao;
    }

    public void createChessGame(final ChessGame chessGame) {
        final Map<Position, Piece> board = chessGame.board();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();
            pieceDao.createPiece(position, piece, gameId);
        }
    }

    public String createChessStatus(final ChessGame chessGame) {
        final String autoGameId = chessStatusDao.create(chessGame.turn().name());
        this.gameId = autoGameId;
        return autoGameId;
    }

    public ChessGame readChessGame() {
        final Map<Position, Piece> board = pieceDao.read(gameId);
        if (board.isEmpty()) {
            return null;
        }

        Color turn = Color.valueOf(chessStatusDao.readTurn(gameId));
        return new ChessGame(new Board(board), turn);
    }

    public ChessGame readChessGame(final String gameId) {
        final Map<Position, Piece> board = pieceDao.read(gameId);
        if (board.isEmpty()) {
            return null;
        }

        Color turn = Color.valueOf(chessStatusDao.readTurn(gameId));
        this.gameId = gameId;
        return new ChessGame(new Board(board), turn);
    }

    public List<String> readGameIds() {
        return chessStatusDao.readGameIds();
    }

    public void deleteAll() {
        pieceDao.deleteAll(gameId);
    }

    public void update(final ChessGame chessGame, final Position from, final Position to) {
        chessStatusDao.update(chessGame.turn().name(), gameId);
        pieceDao.update(from, to, gameId);
    }
}
