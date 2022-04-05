package web.service;

import chess.domain.board.Point;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.domain.piece.Color;
import chess.dto.BoardAndTurnInfo;
import chess.dto.ScoreResponse;
import chess.dto.WebBoardDto;

import java.util.List;

public class ChessService {

    private GameState gameState;

    public ChessService() {
        this.gameState = new Ready();
    }

    public WebBoardDto start() {
        gameState = this.gameState.start();
        return new WebBoardDto((BoardAndTurnInfo) gameState.getResponse(), true);
    }

    public WebBoardDto move(String from, String to) {
        gameState = gameState.move(List.of(Point.of(from), Point.of(to)));
        return new WebBoardDto((BoardAndTurnInfo) gameState.getResponse(), gameState.isRunnable());
    }

    public WebStatusDto status() {
        gameState = gameState.status();
        ScoreResponse response = (ScoreResponse) gameState.getResponse();
        if (response.getWhiteScore() > response.getBlackScore()) {
            return new WebStatusDto(response.getWhiteScore(),
                    response.getBlackScore(), Color.WHITE.toString());
        }
        if (response.getWhiteScore() < response.getBlackScore()) {
            return new WebStatusDto(response.getWhiteScore(),
                    response.getBlackScore(), Color.BLACK.toString());
        }
        return new WebStatusDto(response.getWhiteScore(),
                response.getBlackScore(), "DRAW");
    }

    public void finish() {
        gameState = gameState.finish();
    }
}
