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

public class ChessService {

    private final ChessGameDao chessGameDao;
    private final ChessStatusDao chessStatusDao;

    public ChessService(final ChessGameDao chessGameDao, final ChessStatusDao chessStatusDao) {
        this.chessGameDao = chessGameDao;
        this.chessStatusDao = chessStatusDao;
    }

    public List<String> gameIds() {
        return chessStatusDao.gameIds();
    }

    public ChessGame select(final String gameId) {
        final Map<Position, Piece> board = chessGameDao.select(gameId);
        if (board.isEmpty()) {
            return null;
        }

        Color turn = chessStatusDao.selectTurn(gameId);

        return new ChessGame(new Board(board), turn);
    }

    public String createChessStatus(ChessGame chessGame) {
        return chessStatusDao.create(chessGame);
    }

    public void save(ChessGame chessGame, String gameId) {
        chessGameDao.save(chessGame, gameId);
        chessStatusDao.save(chessGame, gameId);
    }

    public void update(ChessGame chessGame, String gameId) {
        chessGameDao.reset(gameId);
        chessGameDao.save(chessGame, gameId);
        chessStatusDao.save(chessGame, gameId);
        chessStatusDao.update(chessGame, gameId);
    }

    public void reset(final String gameId) {
        chessGameDao.reset(gameId);
    }
}
