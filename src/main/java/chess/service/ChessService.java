package chess.service;

import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.order.MoveResult;
import chess.domain.position.Position;

public class ChessService {
    private ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager();

    public void start() {
        chessGameManager = ChessGameManagerFactory.createRunningGame();
    }

    public MoveResult move(Position from, Position to) {
        return chessGameManager.move(from, to);
    }
}
