package chess.service;

import chess.dao.ChessDao;
import chess.domain.state.GameState;

public class ChessService {
    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
    }

    public void createChessGame(GameState gameState) {
        chessDao.saveGameState(gameState);
        chessDao.saveChessBoard(gameState.getChessBoard());
    }

    public void deleteChessGame() {
        chessDao.deleteChessBoard();
        chessDao.deleteGameState();
    }
}
