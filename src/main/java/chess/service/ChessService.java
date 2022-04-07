package chess.service;

import chess.dao.ChessDao;
import chess.domain.state.GameState;

public class ChessService {
    private final ChessDao chessDao;

    public ChessService() {
        this.chessDao = new ChessDao();
    }

    public void create(GameState gameState) {
        int chessGameId = chessDao.saveGameState(gameState);
        chessDao.saveChessBoard(chessGameId, gameState.getChessBoard());
    }
}
