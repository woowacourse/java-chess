package service;

import dao.ChessGameDao;
import domain.board.ChessGame;
import domain.piece.move.Coordinate;

import java.util.List;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    public ChessGameService(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessGame startGame() {
        chessGameDao.delete();
        return loadGame();
    }

    public ChessGame loadGame() {
        List<Coordinate> coordinates = chessGameDao.read();
        ChessGame chessGame = new ChessGame();

        for (int i = 0; i < coordinates.size(); i += 2) {
            chessGame.move(coordinates.get(i), coordinates.get(i+1));
        }
        return chessGame;
    }

    public void updateMove(final Coordinate start, final Coordinate end) {
        chessGameDao.create(start, end);
    }
}
