package chess.service;

import chess.dao.ChessDao;
import chess.domain.ChessBoardPosition;
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

    public void updateChessBoard(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        chessDao.deleteChessPieceByPosition(targetPosition.getRow(), targetPosition.getColumn());
        chessDao.updateChessBoard(sourcePosition.getRow(), sourcePosition.getColumn(),
                targetPosition.getRow(), targetPosition.getColumn());
    }

    public void updateChessGame(GameState gameState) {
        chessDao.updateGameState(gameState);
    }
}
