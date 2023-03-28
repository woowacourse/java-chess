package chess.service;

import chess.ChessGame;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import dao.ChessGameDao;
import dao.ChessStatusDao;
import java.util.List;
import java.util.Map;

public final class ChessService {

    private final ChessGameDao chessGameDao;
    private final ChessStatusDao chessStatusDao;

    public ChessService(final ChessGameDao chessGameDao, final ChessStatusDao chessStatusDao) {
        this.chessGameDao = chessGameDao;
        this.chessStatusDao = chessStatusDao;
    }

    public List<String> readGameIds() {
        return chessStatusDao.readGameIds();
    }

    public ChessGame readChessGame(final String gameId) {
        final Map<Position, Piece> board = chessGameDao.read(gameId);
        if (board.isEmpty()) {
            return null;
        }

        Color turn = chessStatusDao.readTurn(gameId);

        return new ChessGame(new Board(board), turn);
    }

    public String createChessStatus(ChessGame chessGame) {
        return chessStatusDao.create(chessGame);
    }

    public void createChessGame(ChessGame chessGame, String gameId) {
        final Map<Position, Piece> board = chessGame.board();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();
            chessGameDao.createPiece(position, piece, gameId);
        }
    }

    public void deleteAll(final String gameId) {
        chessGameDao.deleteAll(gameId);
    }

    public void update(final ChessGame chessGame, final Position from, final Position to, final String gameId) {
        chessStatusDao.update(chessGame, gameId);
        chessGameDao.update(from, to, gameId);
    }
}
