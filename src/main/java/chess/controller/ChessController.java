package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.position.Position;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;

public class ChessController {

    private final ChessGame chessGame;

    public ChessController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public boolean canPlay() {
        return chessGame.canPlay();
    }

    public StartResult start() {
        return chessGame.start();
    }

    public MoveResult move(final Position from, final Position to) {
        return chessGame.move(from, to);
    }

    public Score status() {
        return chessGame.calculateScore();
    }

    public EndResult end() {
        return chessGame.end();
    }
}
