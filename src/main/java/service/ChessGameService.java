package service;

import dao.BoardDao;
import domain.game.ChessGame;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.Map;

public class ChessGameService {
    private final BoardDao boardDao;

    public ChessGameService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void saveChessGame(Map<Position, Piece> board, Side lastTurn, boolean gameStatus) {
        boardDao.delete();
        boardDao.save(board, lastTurn, gameStatus);
    }

    public ChessGame loadChessGame() {
        if (boardDao.hasGame()) {
            return boardDao.loadGame();
        }
        throw new IllegalArgumentException("불러올 수 있는 게임이 없습니다.");
    }
}
