package chess.service;

import chess.ChessGame;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import dao.ChessStatusDao;
import dao.DBChessGameDao;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final DBChessGameDao chessGameDao;
    private final ChessStatusDao chessStatusDao;

    public ChessService(final DBChessGameDao chessGameDao, final ChessStatusDao chessStatusDao) {
        this.chessGameDao = chessGameDao;
        this.chessStatusDao = chessStatusDao;
    }

    public List<String> gameIds() {
        return chessStatusDao.gameIds();
    }

    public ChessGame select(final String gameId) {
        final Map<Position, Piece> board = chessGameDao.selectBoard(gameId);
        if (board.isEmpty()) {
            return null;
        }

        Color turn = chessStatusDao.selectTurn(gameId);

        return new ChessGame(new Board(board), turn);
    }

    public String createChessStatus(ChessGame chessGame) {
        return chessStatusDao.createChessStatus(chessGame);
    }

    public void save(ChessGame chessGame, String gameId) {
        chessGameDao.saveChessGame(chessGame, gameId);
        chessStatusDao.saveChessStatus(chessGame, gameId);
    }

    public void update(ChessGame chessGame, String gameId) {
        chessGameDao.resetChessGame(gameId);
        chessGameDao.saveChessGame(chessGame, gameId);
        chessStatusDao.saveChessStatus(chessGame, gameId);
        chessStatusDao.update(chessGame, gameId);
    }

    public void reset(final String gameId) {
        chessGameDao.resetChessGame(gameId);
    }
}
