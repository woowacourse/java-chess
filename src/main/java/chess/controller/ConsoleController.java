package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.StatusResult;

import java.util.Map;

public class ConsoleController {

    private final ChessGame chessGame;

    public ConsoleController() {
        this.chessGame = new ChessGame();
    }

    public boolean isFinish() {
        return chessGame.isFinish();
    }

    public boolean isKingDeath() {
        return chessGame.isKingDeath();
    }

    public Map<Position, Piece> start() {
        chessGame.start();
        return chessGame.getBoard();
    }

    public void end() {
        chessGame.end();
    }

    public Map<Position, Piece> processMove(String source, String target) {
        chessGame.processMove(source, target);
        return chessGame.getBoard();
    }

    public StatusResult processStatus() {
        return chessGame.processStatus();
    }

    public Team getCurrentWinner() {
        return chessGame.getCurrentWinner();
    }
}
