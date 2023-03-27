package service;

import dao.ChessGameDao;
import domain.board.Board;
import domain.board.BoardInitialImage;
import domain.board.ChessGame;
import domain.piece.Piece;
import domain.piece.move.Coordinate;

import java.util.Map;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    public ChessGameService(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessGame startGame() {
        chessGameDao.delete();
        ChessGame chessGame = setupGame();
        chessGameDao.insert(chessGame);
        return loadGame();
    }

    private ChessGame setupGame() {
        Map<Coordinate, Piece> pieceLocations = BoardInitialImage.getCachedBoard();
        Board board = new Board(pieceLocations);
        return new ChessGame(board);
    }

    public ChessGame loadGame() {
        return chessGameDao.read();
    }

    public void updateGame(final ChessGame chessGame) {
        chessGameDao.delete();
        chessGameDao.insert(chessGame);
    }
}
