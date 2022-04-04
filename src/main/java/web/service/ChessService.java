package web.service;

import chess.domain.board.Point;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.BoardAndTurnInfo;
import chess.dto.ScoreResponse;

import java.util.List;

public class ChessService {

    private GameState gameState;

    public ChessService() {
        this.gameState = new Ready();
    }

    public WebBoardDto start() {
        gameState = this.gameState.start();
        return new WebBoardDto((BoardAndTurnInfo) gameState.getResponse());
    }

    public WebBoardDto move(String from, String to) {
        gameState.move(List.of(Point.of(from), Point.of(to)));
        return new WebBoardDto((BoardAndTurnInfo) gameState.getResponse());
    }

    public ScoreResponse status() {
        return (ScoreResponse) gameState.status();
    }

    public void finish() {
        gameState.finish();
    }
}
